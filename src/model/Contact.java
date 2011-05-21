package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import javax.imageio.ImageIO;

public class Contact implements Serializable {

	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private SerializableBufferedImage photo;
	//private BufferedImage photo;
	
	public Contact(	String firstName, 
			String lastName, 
			String phoneNumber,
			String pathToPhoto) {	

		// firstName = new String(firstName); 
		//litters the constant pool; newbie style code.
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		try {
			BufferedImage bufImg = ImageIO.read(new File(pathToPhoto));
			this.photo = new SerializableBufferedImage(bufImg);
		} catch (Exception e) {
			System.out.println("Error while creating SerializableImage...");
		}
	}
	
	public Contact(String firstName, String lastName, String phoneNumber,
			BufferedImage photo) {

		// firstName = new String(firstName);
		// litters the constant pool; newbie style code.
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		try {
			this.photo = new SerializableBufferedImage(photo);
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

	public SerializableBufferedImage getPhoto() {
		return photo;
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

	public void setPhoto(BufferedImage bufImg) {
		try {
			photo = new SerializableBufferedImage(bufImg);
		} catch (Exception e) {
			System.out.println("Error while creating serializablePhoto.");
			e.printStackTrace();
		}
	}

	public void setPhoto(SerializableBufferedImage serBufImg) {
		photo = serBufImg;
	}

}
