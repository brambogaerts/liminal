package nl.brambogaerts.liminal.image;

import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.image.DataBufferInt;

import nl.brambogaerts.liminal.transform.Transformer;

public class Image {
	private BufferedImage img;

	public Image(String url){
		try {
			File f = new File(url);
			BufferedImage buffer = ImageIO.read(f);
			img = new BufferedImage(buffer.getWidth(), buffer.getHeight(), BufferedImage.TYPE_INT_RGB);
			img.getGraphics().drawImage(buffer, 0, 0, null);
		} catch(Exception e){
			fail(e, "Could not load image.");
		}
	}

	public void transform(Transformer t){
		int[] pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
		t.transform(pixels, img.getWidth(), img.getHeight());
	}

	private void fail(Exception e, String message){
		System.out.println(message);
		e.printStackTrace();
		System.exit(1);
	}

	public void save(String url){
		try {
			File f = new File(url);
			ImageIO.write(img, "png", f);
		} catch (Exception e) {
			fail(e, "Could not save image.");
		}
	}
}
