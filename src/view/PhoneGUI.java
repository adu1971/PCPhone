package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import java.util.ArrayList;
import java.util.Iterator;
import model.Contact;
import model.Serializer;

public class PhoneGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private ArrayList<Contact> contactList = null;
	private Contact contact = null;
	private ContactButton contactButton = null;
	private int nCols = 6;	
	private JPanel contactsPanel = new JPanel(new GridLayout(0, nCols));
	private JFrame phoneGUI = this;
	private Serializer ser = new Serializer("./res/contactList.ser");
	
	public PhoneGUI() {
		super();
		this.setTitle("Bilgisayar Telefonu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contactList =  (ArrayList<Contact>) ser.deserialize();
		if (contactList == null) {
			contactList = new ArrayList<Contact>();
		}

		JMenuBar greenMenuBar = new JMenuBar();
		greenMenuBar.setOpaque(true);
		greenMenuBar.setBackground(new Color(154, 165, 127));
		setJMenuBar(greenMenuBar);
		JMenu menu = new JMenu("File");
		greenMenuBar.add(menu);
		
		JMenuItem item1 = new JMenuItem("New Contact");
		item1.addActionListener(newContactListener);
		menu.add(item1);
		
		item1 = new JMenuItem("Delete Contact");
		item1.addActionListener(deleteContactListener);
		menu.add(item1);
		
		item1 = new JMenuItem("Edit Contact");
		item1.addActionListener(editContactListener);
		menu.add(item1);
		
		item1 = new JMenuItem("Exit");
		item1.addActionListener(exitListener);
		menu.add(item1);

		JScrollPane sPane = new JScrollPane(contactsPanel);		
		Dimension sBarDim = sPane.getVerticalScrollBar().getPreferredSize();
		Dimension sBarNewDim = new Dimension(sBarDim.width*3, sBarDim.height);
		sPane.getVerticalScrollBar().setPreferredSize(sBarNewDim);
		sPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(sPane);
		layoutContacts();

		this.setSize(this.getToolkit().getScreenSize()); // maximizes the frame	
		this.setVisible(true);
	}
	
	
	public void layoutContacts() {
		if (contactList==null) return;
		contactsPanel.removeAll();
		Iterator<Contact> it = contactList.iterator();
		while (it.hasNext()) {
			contact = it.next();
			contactButton = new ContactButton(contact, this);
//			contactButton.setPreferredSize(new Dimension(contactWidth, contactHeight));
			contactButton.setBorder(new javax.swing.border.LineBorder(Color.YELLOW));
			contactsPanel.add(contactButton);
		}
		contactsPanel.validate();
		this.validate();
	}
	

	public void actionPerformed(ActionEvent e) {
		System.out.println("adu: in PhoneGUI.actionPerformed()..." + e.getSource());
	}
	
	
	private ActionListener newContactListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Contact ct = null;
			ContactEditor ctEditor = new ContactEditor(ct, phoneGUI);
			int retval = ctEditor.showEditor();
			if (retval == ContactEditor.APPROVE_OPTION) {
				ct = ctEditor.getContact();
				System.out.println(ct);
				contactList.add(ct);
				ser.serialize(contactList);
				layoutContacts();
			} 
		}
	};
	

	private ActionListener deleteContactListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			phoneGUI.setCursor(Cursor
					.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}
	};
	
	
	public void deleteContact(Contact ct) {
		Iterator<Contact> it = contactList.iterator();
		while (it.hasNext()) {
			contact = it.next();
			if (ct.equals(contact)) {
				contactList.remove(contact);
				break;
			}
		}
		ser.serialize(contactList);
		layoutContacts();
	}	


	private ActionListener editContactListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			phoneGUI.setCursor(Cursor
					.getPredefinedCursor(Cursor.HAND_CURSOR));
			} 
	};

	
	public void editContact(Contact ct) {
		ContactEditor ctEditor = new ContactEditor(ct, phoneGUI);
		int retval = ctEditor.showEditor();
		if (retval == ContactEditor.APPROVE_OPTION) {
			ct = ctEditor.getContact();
			System.out.println(ct);
			contactList.add(ct);
			ser.serialize(contactList);
			layoutContacts();
		} 
	}	

	
	private ActionListener exitListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			} 
	};


}
