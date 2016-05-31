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
		calcY();
	}
	
	public void render(Graphics g){
		g.drawImage(bulletImage, (int)getX(), (int)getY(), DRAWNIMAGEWIDTH, DRAWNIMAGEHEIGHT, null);
	}

	private void calcY(){
		double k = dy / dx;
		setY(-k * (getX() - startX) + startY);
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)getX() + 30, (int)getY() + 30, 4, 4);
	}
	
	private void setHorizontalVelocity(){
		horizontalVelocity = dx / dy * 5;
	}
	
}
