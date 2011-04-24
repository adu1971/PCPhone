package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.Contact;


public class ContactButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;	
	
	private Contact contact = null;
	
	public ContactButton(Contact contact) {
		this.contact = contact;
		this.addActionListener(this);
	}

	public Contact getContact() {
		return contact;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g); // paint background
		Image img = contact.getPhoto();
		if (img != null) { // there is a picture: draw it
			int width = this.getSize().width;
			int height = this.getSize().height;
			g.drawImage(img, 0, 0, width, height, this);
		}
	}

//	public Dimension getPreferredSize() {
//		//System.out.println("ContactButton.getPreferredSize() is called...........");
//		 
//		//return new Dimension(image.getWidth(null),
//		// image.getHeight(null));		
//		//Dimension screenSize = this.getToolkit().getScreenSize();
//		//int contactWidth = (screenSize.width - sBarNewDim.width) / nCols;
//		//int contactHeight = (int) (contactWidth * 1.25);
//		
//		Dimension d = super.getPreferredSize();
//		d.setSize(d.width, d.width * 1.25);
//		return d;
//	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("button pressed...");
	}

}
