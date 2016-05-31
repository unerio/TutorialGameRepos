package spriteHandlers;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage myImage;
	
	public SpriteSheet(BufferedImage image){
		myImage = image;
	}
	
	public BufferedImage getImage(int col, int row, int width, int height){
		BufferedImage mySubImage = myImage.getSubimage(col * 32 - 32, row * 32 - 32,
										  			   width, height);
		return mySubImage;
	}
	
}
