package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.TextField;

import javax.swing.JFrame;

public class ContactEditor extends JFrame {

	private static final long serialVersionUID = 1L;

	public ContactEditor() {
		super();
		setTitle("Contact Editor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(new GridBagLayout());
		
		Label label1 = new Label("First Name:");
		Label label2 = new Label("Last Name:");
		Label label3 = new Label("Phone Number:");
		TextField field1 = new TextField(20);
		TextField field2 = new TextField(20);
		TextField field3 = new TextField(20);
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		add(label1, c);
		c.gridy = 1;
		add(label2, c);
		c.gridy = 2;
		add(label3, c);
		
		c.gridx = 1;
		c.gridy = 0;
		add(field1, c);
		c.gridy = 1;
		add(field2, c);
		c.gridy = 2;
		add(field3, c);
		c.gridx = 2;
		c.gridheight = 3;
		add(new ContactButton(null), c);
		
		
	}
}
