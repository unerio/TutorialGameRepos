package spriteHandlers;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage myImage;
	
	public SpriteSheet(BufferedImage image){
		myImage = image;
	}
	
	public BufferedImage getImage(int col, int row, int width, int height){
		BufferedImage mySubImage = myImage.getSubimage(col * 64 - 64, row * 64 - 64,
										  			   width, height);
		return mySubImage;
	}
	
}
