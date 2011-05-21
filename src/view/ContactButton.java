package view;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import model.Contact;

public class ContactButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;	
	
	private PhoneGUI phoneGUI = null;
	private Contact contact = null;
	
	public ContactButton(Contact ct, PhoneGUI owner) {
		super(ct.getPhoneNumber()); //to give the button a certain width.
		contact = ct;
		phoneGUI = owner;
		addActionListener(this);
	}

	public Contact getContact() {
		return contact;
	}

	public void paintComponent(Graphics g) {
		Image img = null;
		super.paintComponent(g); // paint background
		if (contact != null) {
			img = contact.getPhoto().getBufferedImage();			
		}
		if (img != null) { // there is a picture: draw it
			int width = this.getSize().width;
			int height = this.getSize().height;
			g.drawImage(img, 0, 0, width, height, this);
		}
	}

//	public Dimension getPreferredSize() {
////		System.out.println("ContactButton.getPreferredSize() is called...........");
////	 
////		return new Dimension(image.getWidth(null), image.getHeight(null));		
////		Dimension screenSize = this.getToolkit().getScreenSize();
////		int contactWidth = (screenSize.width - sBarNewDim.width) / nCols;
////		int contactHeight = (int) (contactWidth * 1.25);
//		
//		Dimension d = super.getPreferredSize();
//		d.setSize(d.width, d.width * 0.25);
//		return d;
//	}

	public void actionPerformed(ActionEvent e) {
		if (getCursor()==Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR)) {
			getTopLevelAncestor().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			phoneGUI.deleteContact(getContact());
		} else if (getCursor()==Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)) {
			getTopLevelAncestor().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			phoneGUI.editContact(getContact());
		}
	}

}
