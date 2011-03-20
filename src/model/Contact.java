package model;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Contact implements Serializable {
	private String name;
	private String surname;
	private String phoneNumber;
	private BufferedImage photo;

	public Contact(	String name, 
					String surname, 
					String phoneNumber,
					BufferedImage photo) {
		// adı = new String(ad); litters the constant pool; newbie style code.
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.photo = photo;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public BufferedImage getPhote() {
		return photo;
	}

}
