package model;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
 
import javax.imageio.ImageIO;
 
public class SerializableBufferedImage implements Serializable {
 
	private static final long serialVersionUID = 1L;
	private byte[] byteImage = null;
 
	public SerializableBufferedImage(BufferedImage bufferedImage) {
		this.byteImage = toByteArray(bufferedImage);
	}
 
	public BufferedImage getBufferedImage() {
		return fromByteArray(byteImage);
	}
 
	private BufferedImage fromByteArray(byte[] imagebytes) {
		try {
			if (imagebytes != null && (imagebytes.length > 0)) {
				BufferedImage im = ImageIO.read(new ByteArrayInputStream(imagebytes));
				return im;
			}
			return null;
		} catch (IOException e) {
			throw new IllegalArgumentException(e.toString());
		}
	}
 
	private byte[] toByteArray(BufferedImage bufferedImage) {
		if (bufferedImage != null) {
			BufferedImage image = bufferedImage;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				ImageIO.write(image, "png", baos);
			} catch (IOException e) {
				throw new IllegalStateException(e.toString());
			}
			byte[] b = baos.toByteArray();
			return b;
		}
		return new byte[0];
	}
}