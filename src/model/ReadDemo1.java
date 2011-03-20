package model;

import java.io.*;
import java.util.*;
import gnu.io.*;

public class ReadDemo1 implements SerialPortEventListener 
{
	static Enumeration portList;
	static CommPortIdentifier portId; 
	static String output = "";
	SerialPort serialPort;
	static OutputStream outputStream;
	static InputStream inputStream;
	static String dialCommand;
	Thread readThread;
	int temp = 0;

	//*************************
	StringBuffer inputBuffer = new StringBuffer();
	DataOutputStream dout = null;
	OutputStream fos = null;
	String line;
	int ch;
	BufferedReader bos = null;
	int newData = 0;
	boolean writeFlag = false;
	FileInputStream fis = null;

	public static void main(String[] args)
	{
		int ch;
		InputStream fis;
		byte[] size = null;
		int count = 0;

		ReadDemo1 readObject = new ReadDemo1();
/*		try
		{
//			outputStream.write("ATM0L1S0=1&D2&C1S2=36S95=46&Q5\r".getBytes());
			outputStream.write("AT\\K0\r".getBytes());
		}catch(IOException ioe)
		{
			System.out.println("IOException" + ioe);
		}
*/
	}

	void Write(byte[] commandToSend)
	{
	try 
	{
		outputStream.write(commandToSend);
		System.out.println("Successfully Written: " + commandToSend);
	} 
	catch (IOException e) {System.out.println("IO Exception Occured: " + e);}
}
void closePort() 
{
	serialPort.close();
	System.out.print("Serial port closed.");
}


public ReadDemo1() 
{

	portList = CommPortIdentifier.getPortIdentifiers();
	while (portList.hasMoreElements()) 
	{
		portId = (CommPortIdentifier) portList.nextElement();

		if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) 
		{

			if (portId.getName().equals("COM1")) 
			{
				try 
				{
					serialPort = (SerialPort) portId.open("writereadApp", 2000);
					System.out.println("Opened the port for writing: " + portId.getName());
				}
				catch (PortInUseException e) {System.out.print(e);}			

				try 
				{
					serialPort.setSerialPortParams(9600,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);
				}
				catch (UnsupportedCommOperationException e) {System.out.println("UnsupportedCommOperationException, Could not write to the port: " + e);}
				try 
				{
					outputStream = serialPort.getOutputStream();
					inputStream = serialPort.getInputStream();
				} 
				catch (IOException e) {System.out.print(e);}

				try 
				{
					serialPort.addEventListener(this);
				} 
				catch (TooManyListenersException e) {System.out.print(e);}

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
			}
		}
	}
}


	
	public void serialEvent(SerialPortEvent event) 
	{
		switch(event.getEventType()) 
		{
			case SerialPortEvent.DATA_AVAILABLE:
				handleData();	
				break;
			case SerialPortEvent.OE:
			case SerialPortEvent.FE:
			case SerialPortEvent.PE:
			case SerialPortEvent.DSR:
				System.out.println("Data set ready.");
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
				System.out.println("Pick up the receiver.");
				if( event.getNewValue() ) 
				{
					System.out.println("Ring Indicator On");
				}
				else 
				{
					System.out.println("Ring Indicator Off");
				}
				break;
			case SerialPortEvent.CD:
				if( event.getNewValue() ) 
				{
					System.out.println("Connected");
					try
					{
						Thread.sleep(120000);
						while((ch = fis.read()) != -1)
						{
							outputStream.write(ch);
		//								Thread.sleep(15);
							System.out.print((char)ch);
						}
										
						Thread.sleep(180000);					
					}catch(Exception e)
					{
						System.out.println(e);
					}
				}
				else 
				{
					System.out.println("Disconnected");
					System.exit(0);
				}
				break;
		}
	}
	public void handleData()
	{
//		System.out.println(writeFlag);
		System.out.print("Inside serial event");
		try
		{
			fis = new FileInputStream("abc.txt");
		}catch(FileNotFoundException fne) 
		{
			System.out.println("File Does not exists.");
		}
		try
		{
			fos = new FileOutputStream("rmn.txt",true);
			bos = new BufferedReader(new FileReader("rmn.txt"));
			dout = new DataOutputStream(fos);
			int avail = inputStream.available();
			byte[] response = new byte[avail];
			StringBuffer strbuf = new StringBuffer();
			inputStream.read(response, 0, avail);
			for (int i = 0; i < avail; i++) 
			{
				fos.write((char)response[i]);
			}
			if(!writeFlag)
			{
				while((line = bos.readLine())!=null)
				{
				//	dout.flush();
					if(line.equals("CONNECT 9600/ARQ"))
					{
						writeFlag = true;
						System.out.println("connect");
					}
				}
			}
			if(writeFlag)
			{

			}
		}catch(IOException ie1){System.out.println("File " +ie1);}
//		catch(InterruptedException ie1){System.out.println("File " +ie1);}
	}
}