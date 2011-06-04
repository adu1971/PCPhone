package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import model.Contact;
import model.Dialer;

public class ContactButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;	
	private PhoneGUI phoneGUI = null;
	private Contact contact = null;
	
	public ContactButton(Contact ct, Dimension d, PhoneGUI owner) {
		super(); 
		contact = ct;
		ImageIcon imgIcon = new ImageIcon(contact.getPhoto().getBufferedImage());
		imgIcon.setImage(imgIcon.getImage().getScaledInstance(d.width, d.height, Image.SCALE_FAST));
		setIcon(imgIcon);
		setBorder(new javax.swing.border.LineBorder(Color.RED));
		setToolTipText(contact.getPhoneNumber());
		phoneGUI = owner;
		addActionListener(this);
	}
    
	public Contact getContact() {
		return contact;
	}	

	public void actionPerformed(ActionEvent e) {
		if (getCursor()==Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR)) {
			getTopLevelAncestor().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			phoneGUI.deleteContact(getContact());
		} else if (getCursor()==Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)) {
			getTopLevelAncestor().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			phoneGUI.editContact(getContact());
		} else {
			new Dialer(contact.getPhoneNumber());
		}
	}

}
