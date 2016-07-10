package gameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import spriteHandlers.Animation;

public class Enemy extends GameObject{
	
	private double startY;
	
	private static final int AMPLITUDE = 50;
	private static final int FREQUENCY = 150;
	private static final int MINYPOSITION = 50;
	private static final int MAXYPOSITION = 150;
	private static final int VISIBLEXPOSITION = 0;
	private static final int IMAGEWIDTH = 64;
	private static final int IMAGEHEIGHT = 64;
	private static final int MAXXPOSITION = 900;
	private static final int STARTXPOSITION = 750;

	private static final double VELOCITY = 1.2;
	
	
	private Animation enemyAnimation;
	private BufferedImage tempSplitImageFront = null;
	private BufferedImage tempSplitImageBack = null;
	
	private boolean splitting;
	
	public Enemy(BufferedImage[] animationFrames){
		super(0, 0);
		this.startY = Math.random() * MAXYPOSITION + MINYPOSITION;
		setX(STARTXPOSITION);
		setY(Math.sin((Math.PI / FREQUENCY) * getX()) * AMPLITUDE + startY);
		this.enemyAnimation = new Animation(200, animationFrames[0], animationFrames[1],
												 animationFrames[2]);
	}
	
	@Override
	public void tick(){
		setX(getX() + VELOCITY);
		setY(Math.sin((Math.PI / FREQUENCY) * getX()) * AMPLITUDE + startY);
		if(getX() > MAXXPOSITION){
			setX(VISIBLEXPOSITION);
			splitting = false;
			tempSplitImageFront = null;
			tempSplitImageBack = null;
		}
		else if(getX() + IMAGEWIDTH > MAXXPOSITION){
			tempSplitImageFront = enemyAnimation.getCurrImage().getSubimage(IMAGEWIDTH - ((int)getX() +
															IMAGEWIDTH - MAXXPOSITION) - 1,
														 0,
												    	 (int)getX() + IMAGEWIDTH - MAXXPOSITION + 1,
												    	 IMAGEHEIGHT);
			tempSplitImageBack = enemyAnimation.getCurrImage().getSubimage(0, 0,
				    									IMAGEWIDTH - ((int)getX() +
				    										IMAGEWIDTH - MAXXPOSITION),
				    									IMAGEHEIGHT);
			splitting = true;
		}
		
		enemyAnimation.runAnimation();
	}
	
	@Override
	public void render(Graphics g){
		if(splitting){
			g.drawImage(tempSplitImageBack, (int)getX(), (int)getY(), null);
			g.drawImage(tempSplitImageFront, VISIBLEXPOSITION, (int)getY(), null);
			System.out.println("Anders: " + getX());
		}
		else{
			enemyAnimation.drawAnimation(g, getX(), getY(), 0);
			System.out.println("Normal: " + getX());
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)getX() + 3 * IMAGEWIDTH / IMAGEWIDTH,
							 (int)getY() + 10 * IMAGEHEIGHT / IMAGEHEIGHT,
							 IMAGEWIDTH - 6 * IMAGEWIDTH / IMAGEWIDTH,
							 IMAGEHEIGHT - 20 * IMAGEHEIGHT / IMAGEHEIGHT);
	}
}
