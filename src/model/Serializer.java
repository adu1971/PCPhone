package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.File;

public class Serializer {
	
	private File serializationFile = null;

	public Serializer(String fileName) {
		serializationFile = new File(fileName);
		if (!serializationFile.exists()) {
			try {
				serializationFile.createNewFile();
			} catch (IOException e) {
				System.out.println("adu: Serializer could not create a new serialization file!");
			}
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
			System.out.println("adu: IOException in " + this.getClass().getName());
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
