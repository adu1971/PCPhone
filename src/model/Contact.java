package model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;

public class Contact implements Serializable {

	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private SerializableImage photo;
	
	public Contact(	String firstName, 
			String lastName, 
			String phoneNumber,
			String pathToPhoto) {	
		BufferedImage bPhoto = null;		
		try {
			bPhoto = ImageIO.read(new File(pathToPhoto));
		} catch (IOException e) {
			System.out.println("IOException while reading dummy image...");
		}
		new Contact(firstName, lastName, phoneNumber, bPhoto);	
	};
	
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
		} catch (Exception e) {
			System.out.println("Error while creating serializablePhoto.");
			e.printStackTrace();
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public Image getPhoto() {
		if (this.photo==null) {
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
			System.out.println(this.firstName);
		} 			
		return photo.getImage();
	}

	public void setFirstName(String fName) {
		firstName = fName;
	}

	public void setLastName(String lName) {
		lastName = lName;
	}

	public void setPhoneNumber(String pNumber) {
		phoneNumber = pNumber;
	}

	public void setPhoto(BufferedImage p) {
		try {
			this.photo = new SerializableImage(p);
		} catch (Exception e) {
			System.out.println("Error while creating serializablePhoto.");
			e.printStackTrace();
		}
	}

}
