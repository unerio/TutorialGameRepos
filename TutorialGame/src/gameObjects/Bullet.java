package gameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject{
	
	private final int DRAWNIMAGEWIDTH = 64;
	private final int DRAWNIMAGEHEIGHT = 64;
	
	private double horizontalVelocity;
	private double dx;
	private double dy;
	private double startX;
	private double startY;
	
	private BufferedImage bulletImage;
	
	public Bullet(double x, double y, double dx, double dy, BufferedImage bulletImage){
		super(x, y);
		startX = x;
		startY = y;
		this.bulletImage = bulletImage;
		this.dx = dx;
		this.dy = dy;
		setHorizontalVelocity();
	}
	
	public void tick(){
		setX(getX() + horizontalVelocity);
		setY(-dy/dx * (getX() - startX) + startY);
	}
	
	public void render(Graphics g){
		g.drawImage(bulletImage, (int)getX(), (int)getY(), DRAWNIMAGEWIDTH, DRAWNIMAGEHEIGHT, null);
		System.out.println("y: " + getY());
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)getX() + 30, (int)getY() + 30, 4, 4);
	}
	
	private void setHorizontalVelocity(){
		horizontalVelocity = 5.0;
		if(dx < 0){
			horizontalVelocity *= -1;
		}
	}
	
}
