package model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Contact implements Serializable {

	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private SerializableImage photo;

	public Contact(	String firstName, 
					String lastName, 
					String phoneNumber,
					BufferedImage photo) {
		// firstName = new String(firstName); 
		//litters the constant pool; newbie style code.
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		try {
			this.photo = new SerializableImage(photo);
		} catch (InterruptedException e) {
			System.out.println("Error while creating serializablePhoto.");
			e.printStackTrace();
		}
	}

	public String getfirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public Image getPhoto() {
		return photo.getImage();
	}


}
