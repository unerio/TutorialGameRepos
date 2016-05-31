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
	private static final int VISIBLEXPOSITION = 2;
	private static final int IMAGEWIDTH = 32;
	private static final int IMAGEHEIGHT = 32;
	private static final int DRAWNIMAGEWIDTH = 64;
	private static final int DRAWNIMAGEHEIGHT = 64;
	private static final int MAXXPOSITION = 900 + DRAWNIMAGEWIDTH / 2;
	private static final int STARTXPOSITION = -DRAWNIMAGEWIDTH;

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
		this.enemyAnimation = new Animation(200, DRAWNIMAGEWIDTH, DRAWNIMAGEHEIGHT,
											animationFrames[0], animationFrames[1], animationFrames[2]);
	}
	
	@Override
	public void tick(){
		setX(getX() + VELOCITY);
		setY(Math.sin((Math.PI / FREQUENCY) * getX()) * AMPLITUDE + startY);
		if(getX() + IMAGEWIDTH > MAXXPOSITION){
			setX(VISIBLEXPOSITION);
			splitting = false;
			tempSplitImageFront = null;
			tempSplitImageBack = null;
		}
		else if(getX() + DRAWNIMAGEWIDTH > MAXXPOSITION){
			tempSplitImageFront = enemyAnimation.getCurrImage().getSubimage(IMAGEWIDTH - ((int)getX() +
															DRAWNIMAGEWIDTH - MAXXPOSITION) - 1,
														 0,
												    	 (int)getX() + DRAWNIMAGEWIDTH - MAXXPOSITION + 1,
												    	 IMAGEHEIGHT);
			tempSplitImageBack = enemyAnimation.getCurrImage().getSubimage(0, 0,
				    									IMAGEWIDTH - ((int)getX() +
				    										DRAWNIMAGEWIDTH - MAXXPOSITION),
				    									IMAGEHEIGHT);
			splitting = true;
		}
		
		enemyAnimation.runAnimation();
	}
	
	@Override
	public void render(Graphics g){
		if(splitting){
			g.drawImage(tempSplitImageBack,
							(int)getX(), (int) getY(),
							(IMAGEWIDTH - ((int)getX() +
								DRAWNIMAGEWIDTH - MAXXPOSITION) - 1) * 2, DRAWNIMAGEHEIGHT,
							null);
			g.drawImage(tempSplitImageFront,
							VISIBLEXPOSITION, (int) getY(),
							((int)getX() -
								MAXXPOSITION + DRAWNIMAGEWIDTH + 1) * 2, DRAWNIMAGEHEIGHT,
							null);
		}
		else{
			enemyAnimation.drawAnimation(g, getX(), getY(), 0);
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)getX() + 3 * DRAWNIMAGEWIDTH / IMAGEWIDTH,
							 (int)getY() + 10 * DRAWNIMAGEHEIGHT / IMAGEHEIGHT,
							 DRAWNIMAGEWIDTH - 6 * DRAWNIMAGEWIDTH / IMAGEWIDTH,
							 DRAWNIMAGEHEIGHT - 20 * DRAWNIMAGEHEIGHT / IMAGEHEIGHT);
	}
}
