package spriteHandlers;

import java.awt.image.BufferedImage;

public class Textures {
	
	private SpriteSheet sprites;
	
	private BufferedImage[] playerAnimation = new BufferedImage[2];
	private BufferedImage bulletImage;
	private BufferedImage[] enemyAnimation = new BufferedImage[3];
	private BufferedImage crosshairImage;
	
	public Textures(BufferedImage spriteSheet){
		sprites = new SpriteSheet(spriteSheet);
		
		directTextures();
	}
	
	private void directTextures(){
		playerAnimation[0] = sprites.getImage(1, 1, 64, 64);
		playerAnimation[1] = sprites.getImage(2, 1, 64, 64);
		
		bulletImage = sprites.getImage(1, 2, 32, 32);
		
		enemyAnimation[0] = sprites.getImage(1, 3, 64, 64);
		enemyAnimation[1] = sprites.getImage(2, 3, 64, 64);
		enemyAnimation[2] = sprites.getImage(3, 3, 64, 64);
		
		crosshairImage = sprites.getImage(1, 4, 64, 64);
	}
	
	public BufferedImage[] getPlayerAnimation(){
		return playerAnimation;
	}
	
	public BufferedImage getBulletImage(){
		return bulletImage;
	}
	
	public BufferedImage[] getEnemyAnimation(){
		return enemyAnimation;
	}
	
	public BufferedImage getCrosshairImage(){
		return crosshairImage;
	}
	
}
