package mainClasses;

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
		playerAnimation[0] = sprites.getImage(1, 1, 32, 32);
		playerAnimation[1] = sprites.getImage(2, 1, 32, 32);
		
		bulletImage = sprites.getImage(1, 2, 32, 32);
		
		enemyAnimation[0] = sprites.getImage(1, 3, 32, 32);
		enemyAnimation[1] = sprites.getImage(2, 3, 32, 32);
		enemyAnimation[2] = sprites.getImage(3, 3, 32, 32);
		
		crosshairImage = sprites.getImage(1, 4, 32, 32);
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
