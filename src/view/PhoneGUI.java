package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import java.util.ArrayList;
import java.util.Iterator;

import model.Contact;

public class PhoneGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private int nCols = 6;	

	public PhoneGUI(ArrayList<Contact> contactList) {
		super();
		this.setTitle("Bilgisayar Telefonu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar greenMenuBar = new JMenuBar();
		greenMenuBar.setOpaque(true);
		greenMenuBar.setBackground(new Color(154, 165, 127));
		setJMenuBar(greenMenuBar);
		JMenu menu = new JMenu("File");
		greenMenuBar.add(menu);
		JMenuItem item1 = new JMenuItem("New Contact");
		


		
		item1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ContactEditor(null);
			}
		});
		menu.add(item1);
		
		item1 = new JMenuItem("Exit");
		menu.add(item1);

		JPanel contactsPanel = new JPanel(new GridLayout(0, nCols));
		JScrollPane sPane = new JScrollPane(contactsPanel);		
		Dimension sBarDim = sPane.getVerticalScrollBar().getPreferredSize();
		Dimension sBarNewDim = new Dimension(sBarDim.width*3, sBarDim.height);
		sPane.getVerticalScrollBar().setPreferredSize(sBarNewDim);
		sPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(sPane);

		Contact contact = null;
		ContactButton contactButton = null;
		Iterator<Contact> it = contactList.iterator();
		while (it.hasNext()) {
			contact = it.next();
			contactButton = new ContactButton(contact);
//			contactButton.setPreferredSize(new Dimension(contactWidth, contactHeight));
			contactButton.setBorder(new javax.swing.border.LineBorder(Color.BLACK));
			contactsPanel.add(contactButton);
			
		}
		this.setSize(this.getToolkit().getScreenSize()); // maximizes the frame	
		this.setVisible(true);
		//this.pack();
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getSource());
		
	}

}
