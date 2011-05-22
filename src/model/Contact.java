package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import javax.imageio.ImageIO;

public class Contact implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String phoneNumber;
	private SerializableBufferedImage photo;
	//private BufferedImage photo;
	
	public Contact(	String name, 
			String phoneNumber,
			String pathToPhoto) {	

		// firstName = new String(firstName); 
		//litters the constant pool; newbie style code.
		this.name = name;
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
		this.name = name;
		this.phoneNumber = phoneNumber;
		try {
			this.photo = new SerializableBufferedImage(photo);
		} catch (Exception e) {
			System.out.println("Error while creating serializablePhoto.");
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public SerializableBufferedImage getPhoto() {
		return photo;
	}

	public void setName(String name) {
		this.name = name;
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
