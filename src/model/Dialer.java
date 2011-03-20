package model;

import java.io.*;
import java.util.*;
import gnu.io.*;

public class Dialer implements Runnable, SerialPortEventListener {

	static CommPortIdentifier portId;
	static Enumeration portList;

	InputStream inputStream;
	OutputStream outputStream;
	SerialPort serialPort;
	Thread dialerThread;
	String telNo;
	String dialCommand;
	static FileOutputStream fos = null;
	static FileOutputStream output = null;
	boolean flag = false;
	int conCount = 0;

	public Dialer(String[] args) {
		try {
			fos = new FileOutputStream("dem.txt", true);
			output = new FileOutputStream("abc.txt", true);
		} catch (Exception e) {
		}
		if (args.length != 1) {
			System.err.println("Usage: Dialup ");
			return;
		}
		portList = CommPortIdentifier.getPortIdentifiers();
		while (portList.hasMoreElements()) {
			portId = (CommPortIdentifier) portList.nextElement();
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				if (portId.getName().equals("COM3")) {
					dial(args[0]);
				}
			}
		}
	}

	
	public void dial(String telNo) {
		int mSecsToWait = 2000;
		this.telNo = telNo;
		this.dialCommand = "ATDT" + telNo + "\r";
		try {
			serialPort = (SerialPort) portId.open("PCPhone", mSecsToWait);
			serialPort.addEventListener(this);
			inputStream = serialPort.getInputStream();
			outputStream = serialPort.getOutputStream();
			serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		} catch (PortInUseException e) {
			System.out.println(" adu: Port in use " + e);
		} catch (IOException e) {
			System.out.println(" Hell " + e);
		} catch (TooManyListenersException e) {
			System.out.println("TooManyListenersException. "+ e);
		} catch (UnsupportedCommOperationException e) {
			System.out.println("Could not write to the port. "+ e);
		}

		serialPort.notifyOnDataAvailable(true);
		serialPort.notifyOnCarrierDetect(true);
		serialPort.notifyOnDataAvailable(true);
		serialPort.notifyOnBreakInterrupt(true);
		serialPort.notifyOnCTS(true);
		serialPort.notifyOnDSR(true);
		serialPort.notifyOnFramingError(true);
		serialPort.notifyOnOutputEmpty(true);
		serialPort.notifyOnOverrunError(true);
		serialPort.notifyOnParityError(true);
		serialPort.notifyOnRingIndicator(true);

		dialerThread = new Thread(this);
		dialerThread.start();
	}

	
	public void run() {
		while (true) {
			if (conCount == 0) {
				try {
					if (!serialPort.isCD()) {
						System.err.println("Try to connect ...");
						outputStream.write(dialCommand.getBytes());
					}
					Thread.sleep(60000);
				} catch (Exception ex) {
					System.err.println(ex);
				}
			} 
		}
	}

	
	public void serialEvent(SerialPortEvent event) {
		switch (event.getEventType()) {
		case SerialPortEvent.OE:
		case SerialPortEvent.FE:
		case SerialPortEvent.PE:
		case SerialPortEvent.DSR:
			System.out.println("Data Set Ready.");
			break;
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
			System.out.println("Ignored event");
			break;

		case SerialPortEvent.BI:
			System.out.println("Break Interrupt");
			break;

		case SerialPortEvent.CTS:
			System.out.println("Clear to send");
			break;

		case SerialPortEvent.RI:
			if (event.getNewValue()) {
				System.out.println("Ring Indicator On");
			} else {
				System.out.println("Ring Indicator Off");
			}
			break;

		case SerialPortEvent.CD:
			if (event.getNewValue()) {
				System.out.println("Connected");
				conCount = conCount + 1;
				flag = true;
			} else {
				System.out.println("Disconnected");
			}
			break;

		case SerialPortEvent.DATA_AVAILABLE:
			handleData();
			break;
		}
	}

	
	public void handleData() {
		try {
			int avail = inputStream.available();
			byte[] response = new byte[avail];
			inputStream.read(response, 0, avail);
			for (int i = 0; i < avail; i++) {
				if (!flag) {
					fos.write((char) response[i]);
				}
			}
			if (flag) {
				inputStream.read(response, 0, avail);
				for (int i = 0; i < avail; i++) {
					Thread.sleep(5);
					output.write((char) response[i]);
					System.out.print((char) response[i]);
				}
			}
		} catch (IOException ie1) {
			System.out.println("File " + ie1);
		} catch (InterruptedException in) {
			System.out.println("Interrupt " + in);
		}
	}
	
	
}