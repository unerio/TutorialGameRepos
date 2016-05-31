package mainClasses;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import gameObjects.Bullet;
import gameObjects.Enemy;
import gameObjects.GameObject;

public class Controller {

	private LinkedList<GameObject> gameObjects = new LinkedList<>();
	
	private BufferedImage bulletImage;
	private BufferedImage[] enemyAnimation;
	
	public Controller(BufferedImage bulletImage,
					  BufferedImage[] enemyAnimation){
		this.bulletImage = bulletImage;
		this.enemyAnimation = enemyAnimation;
	}
	
	public void tick(){
		for(int ind = 0; ind < gameObjects.size(); ind++){
			if(gameObjects.get(ind) != null){
				gameObjects.get(ind).tick();
				if(gameObjects.get(ind) instanceof Bullet){
					if(gameObjects.get(ind).getY() < -30){
						removeObject(gameObjects.get(ind));
					}
				}
			}
		}
	}
	
	public void render(Graphics g){
		for(int ind = 0; ind < gameObjects.size(); ind++){
			if(gameObjects.get(ind) != null){
				gameObjects.get(ind).render(g);
			}
		}
	}
	
	public void addBullet(double x, double y, double dx, double dy){
		Bullet newBullet = new Bullet(x, y, dx, dy, bulletImage);
		gameObjects.add(newBullet);
	}
	
	
	public void addEnemy(){
		Enemy newEnemy = new Enemy(enemyAnimation);
		gameObjects.add(newEnemy);
	}
	
	public void removeObject(GameObject oldObject){
		gameObjects.remove(oldObject);
	}
	
	public LinkedList<GameObject> getObjects(){
		return gameObjects;
	}
	
}
