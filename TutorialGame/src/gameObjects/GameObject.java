package gameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

	private double x;
	private double y;
	
	public GameObject(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public void setX(double x){
		this.x = x;
	}
	
	public double getX(){
		return x;
	}
	
	public void setY(double y){
		this.y = y;
	}
	
	public double getY(){
		return y;
	}
	
}
