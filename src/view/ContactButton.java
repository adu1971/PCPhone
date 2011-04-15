package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ContactButton extends JButton implements ActionListener {

	Image image = null;

	public ContactButton(Image img) {
		image = img;
		this.addActionListener(this);
	}

	public Image getImage(Image img) {
		return image;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g); // paint background
		if (image != null) { // there is a picture: draw it
			int width = this.getSize().width;
			int height = this.getSize().height;
			g.drawImage(image, 0, 0, width, height, this);
		}
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("button pressed...");
	}

}
