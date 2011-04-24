package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import view.*;
import model.*;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<Contact> contactList = null;
		String serializationFile = "contactList.ser";
		
		PropertyManager rm = new PropertyManager();
		Properties props = rm.readProperties();
		
		Serializer ser = new Serializer();
		contactList =  (ArrayList<Contact>) ser.deserialize(serializationFile);
		
		if (contactList==null) contactList = new ArrayList<Contact>();
		//populateContactList(contactList);
		ser.serialize(contactList, serializationFile);
		PhoneGUI phone = new PhoneGUI(contactList);
	}
	
	public static void populateContactList(ArrayList<Contact> list) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("./res/Aybuke_20050828.jpg"));
		} catch (IOException e) {
			System.out.println("IOException while reading image file...");
		}

		Contact contact1 = null;

		for (int i = 0; i < 4; i++) {
			contact1 = new Contact("hasan", "güngör", "888", img);
			list.add(contact1);
		}
	}

}
