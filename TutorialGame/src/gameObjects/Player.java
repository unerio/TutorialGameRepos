package gameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import mainClasses.Controller;
import spriteHandlers.Animation;

public class Player extends GameObject{

	private final int MINXPOSITION = 0;
	private final int MAXXPOSITION = 835;
	private final int MINYPOSITION = 0;
	private final int MAXYPOSITION = 535;
	private final int DRAWNIMAGEWIDTH = 64;
	private final int DRAWNIMAGEHEIGHT = 64;
	
	private double velX;
	private double velY;
	
	private boolean moveUp;
	private boolean moveLeft;
	private boolean moveDown;
	private boolean moveRight;
	
	private Animation playerAnimation;
	
	public Player(double x, double y, BufferedImage[] animationFrames){
		super(x, y);
		this.playerAnimation = new Animation(200, DRAWNIMAGEWIDTH, DRAWNIMAGEHEIGHT,
											 animationFrames[0], animationFrames[1]);
		this.moveUp = false;
		this.moveLeft = false;
		this.moveDown = false;
		this.moveRight = false;
	}
	
	@Override
	public void tick(){
		setX(getX() + velX);
		setY(getY() + velY);
		if(getX() <= MINXPOSITION){
			setX(MINXPOSITION);
		}
		if(getX() >= MAXXPOSITION){
			 setX(MAXXPOSITION);
		}
		if(getY() <= MINYPOSITION){
			setY(MINYPOSITION);
		}
		if(getY() >= MAXYPOSITION){
			setY(MAXYPOSITION);
		}
		
		playerAnimation.runAnimation();
		move();
	}
	
	public void render(Graphics g){
		playerAnimation.drawAnimation(g, getX(), getY(), 0);
	}
	
	public void setMovement(String direction, boolean startMoving){
		if("up".equals(direction)){
			moveUp = startMoving;
			if(startMoving){
				moveDown = false;
			}
		}
		if("left".equals(direction)){
			moveLeft = startMoving;
			if(startMoving){
				moveRight = false;
			}
		}
		if("down".equals(direction)){
			moveDown = startMoving;
			if(startMoving){
				moveUp = false;
			}
		}
		if("right".equals(direction)){
			moveRight = startMoving;
			if(startMoving){
				moveLeft = false;
			}
		}
	}
	
	public void move(){
		if(moveUp){
			velY = -2;
		}
		if(moveLeft){
			velX = -2;
		}
		if(moveDown){
			velY = 2;
		}
		if(moveRight){
			velX = 2;
		}
		if(!moveRight && !moveLeft){
			velX = 0;
		}
		if(!moveUp && !moveDown){
			velY = 0;
		}
		
	}
	
	public void shoot(Controller bulletController, int chX, int chY){
		bulletController.addBullet(getX(), getY() - 30, chX - getX(), getY() - chY);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)getX() + 6, (int)getY() + 1, 20, 25);
	}
	
}
