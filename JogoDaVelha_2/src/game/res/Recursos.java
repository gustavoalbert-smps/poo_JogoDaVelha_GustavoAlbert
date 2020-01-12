package game.res;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Recursos {
	
	public static BufferedImage[] letters;
	
	static {
		letters = new BufferedImage[2];
		letters[0] = loadImage("/X.png");
		letters[1] = loadImage("/CIRCLE.png");
	}
	
	private static BufferedImage loadImage (String path) {
		try {
			return ImageIO.read(Class.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return null;
	}
}
