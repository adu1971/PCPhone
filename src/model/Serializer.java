package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class Serializer {
	
	public Object deserialize(String fileName) {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		Object obj = null;
		try {
			fis = new FileInputStream(fileName);
			ois = new ObjectInputStream(fis);
//			serializationObj = serializationObj.getClass().cast(ois.readObject());
// 			print out the size
//			System.out.println("Temas listeside " + contactList.size() + "kayıt var.");
			obj = ois.readObject();
			ois.close();
			fis.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return obj;
	}
	
	public boolean serialize(Object obj, String fileName) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(fileName);
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
