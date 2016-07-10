package gameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject{
	
	private final double TOTALVELOCITY = 8;
	
	private double horizontalVelocity;
	private double verticalVelocity;
	private double dx;
	private double dy;
	
	private BufferedImage bulletImage;
	
	public Bullet(double x, double y, double dx, double dy, BufferedImage bulletImage){
		super(x, y);
		this.bulletImage = bulletImage;
		this.dx = dx - 16;
		this.dy = dy - 16;
		calcVelocities();
	}
	
	public void tick(){
		setX(getX() + horizontalVelocity);
		setY(getY() + verticalVelocity);
	}
	
	public void render(Graphics g){
		g.drawImage(bulletImage, (int)getX(), (int)getY(), null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)getX() + 30, (int)getY() + 30, 4, 4);
	}
	
	private void calcVelocities(){
		double distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
		double partOfDistance = TOTALVELOCITY / distance;
		horizontalVelocity = dx * partOfDistance;
		verticalVelocity = -dy * partOfDistance;
	}
	
}
