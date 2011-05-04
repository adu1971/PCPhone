package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import model.Contact;

public class ContactEditor extends JFrame {

	private static final long serialVersionUID = 1L;
	private Contact dummyContact; 
	
	public ContactEditor(Contact contact) {
		super();
		setTitle("Contact Editor");		
		Label label1 = new Label("First Name:");
		Label label2 = new Label("Last Name:");
		Label label3 = new Label("Phone Number:");
		TextField fieldFirstName = new TextField(20);
		TextField fieldLastName = new TextField(20);
		TextField fieldPhoneNumber = new TextField(20);
		JButton okButton = new JButton("Save");
		JButton cancelButton = new JButton("Cancel");
		
		dummyContact = new Contact(
				"Ad giriniz...",
				"Soyad Giriniz...", 
				"Telefon giriniz...",
				"./res/list-add-contact.png");
		if (contact==null) contact = dummyContact;
		ContactButton ctButton = new ContactButton(dummyContact);
		fieldFirstName.setText(contact.getFirstName());
		fieldLastName.setText(contact.getLastName());
		fieldPhoneNumber.setText(contact.getPhoneNumber());


//		okButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if (!fieldPhoneNumber.getText().isEmpty() & 
//				(!fieldFirstName.getText().isEmpty() | 
//						!fieldLastName.getText().isEmpty())) {
//					contact.setFirstName(fieldFirstName.getText());
//					contact.setLastName(fieldLastName.getText());
//					contact.setPhoneNumber(fieldPhoneNumber.getText());
//					contact.setPhoto(ctButton.getContact().getBufferedPhoto());
//				}
//			}
//		});
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		add(label1, c);
		c.gridy = 1;
		add(label2, c);
		c.gridy = 2;
		add(label3, c);
		c.gridy = 3;
		add(okButton, c);
		
		c.gridx = 1;
		c.gridy = 0;
		add(fieldFirstName, c);
		c.gridy = 1;
		add(fieldLastName, c);
		c.gridy = 2;
		add(fieldPhoneNumber, c);
		c.gridy = 3;
		add(cancelButton, c);
			
		c.gridx = 2;
		c.gridy = 0;
		c.gridheight = 3;
		c.weightx = 1.0;
		//c.fill = GridBagConstraints.BOTH;
		add(ctButton, c);
		setVisible(true);
		this.pack();
		this.setLocationRelativeTo(null);		
	}

}
