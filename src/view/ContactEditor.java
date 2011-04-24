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

	public ContactEditor(Contact contact) {
		super();
		setTitle("Contact Editor");
		setVisible(true);
		setLayout(new GridBagLayout());
		
		Label label1 = new Label("First Name:");
		Label label2 = new Label("Last Name:");
		Label label3 = new Label("Phone Number:");
		TextField field1 = new TextField(20);
		TextField field2 = new TextField(20);
		TextField field3 = new TextField(20);
		JButton okButton = new JButton("Save");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				//***********************
			}
		});
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
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
		add(field1, c);
		c.gridy = 1;
		add(field2, c);
		c.gridy = 2;
		add(field3, c);
		c.gridy = 3;
		add(cancelButton, c);
			
		c.gridx = 2;
		c.gridy = 0;
		c.gridheight = 3;
		c.weightx = 1.0;
		c.fill = GridBagConstraints.BOTH;
		add(new ContactButton(null), c);
		this.pack();
		this.setLocationRelativeTo(null);		
	}
}
