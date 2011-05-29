package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.File;

public class Serializer {
	
	private File serializationFile = null;

	public Serializer() {

		try {
			serializationFile = new File("TelefonDefteri.ser");
			if (!serializationFile.exists()) {
				serializationFile.createNewFile();
			}
		} catch (Exception e) {
			System.err.println("adu: Serializer could not create a new serialization file!");
			System.out.println(System.getProperty("user.dir"));
			e.printStackTrace();
		}
	}
	
	public Object deserialize() {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		Object obj = null;
		try {
			fis = new FileInputStream(serializationFile);
			ois = new ObjectInputStream(fis);
			obj = ois.readObject();
			ois.close();
			fis.close();
		} catch (IOException e) {
			System.err.println("adu: IOException in " + this.getClass().getName());
			e.printStackTrace(); 
		} catch (ClassNotFoundException e) {
			System.out.println("adu: ClassNotFoundException in " + this.getClass().getName());
			e.printStackTrace();
		}
		return obj;
	}
	
	public boolean serialize(Object obj) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(serializationFile);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);
			oos.close();
			fos.close();
			return true;
		} catch (IOException ex) {
			ex.printStackTrace();
		} 
		return false;
	}
}
