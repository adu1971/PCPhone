package model;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.io.Serializable;

public class SerializableImage implements Serializable {

	private static final long serialVersionUID = 1L;
	private int width = 0;
	private int height = 0;
	private int[] pixels;

	public SerializableImage(BufferedImage img) throws InterruptedException {
		width = img.getWidth();
		height = img.getHeight();
		pixels = getArrayFromImage(img, width, height);
	}
	
	public Image getImage() {
		MemoryImageSource mis = new MemoryImageSource(width, height, pixels, 0,
				width);
		Toolkit tk = Toolkit.getDefaultToolkit();
		return tk.createImage(mis);	
	}

	private int[] getArrayFromImage(Image img, int width, int height)
			throws InterruptedException {
		pixels = new int[width * height];
		PixelGrabber pg = new PixelGrabber(img, 0, 0, width, height, pixels, 0,
				width);
		pg.grabPixels();
		return pixels;
	}
}
