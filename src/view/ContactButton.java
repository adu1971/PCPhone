package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class ContactButton extends JButton {

	Image image = null;

	public ContactButton() {
	}
	
	public ContactButton(Image img) {
		setImage(img);
		//this.addActionListener(this);
	}

	public void setImage(Image img) {
		this.image = img;
	}

	public Image getImage(Image img) {
		return image;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g); // paint background
		if (image != null) { // there is a picture: draw it
			int height = this.getSize().height;
			int width = this.getSize().width;
			// g.drawImage(image, 0, 0, this); //use image size
			g.drawImage(image, 0, 0, width, height, this);
			// g.drawImage(image, 0, 0, 200, 200, this);
		}
	}

	// public Dimension getPreferredSize() {
	// if (image == null) {
	// return new Dimension(100, 100);
	// } else {
	// //return new Dimension(image.getWidth(null), image.getHeight(null));
	// Dimension d = super.getPreferredSize();
	// d.setSize(d.width, d.width * 4);
	// return d;
	// }
	// }

	public void actionPerformed(ActionEvent e) {

	}

}
