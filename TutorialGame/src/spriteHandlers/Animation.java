package spriteHandlers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Animation {
	
	private int timeOnScreen;
	private int frames;
	private int count = 0;
	
	private double timeOfLastFrame = 0;
	private double currTime = 0;
	
	private LinkedList<BufferedImage> animationFrames;
	
	private BufferedImage currentImg;
	
	public Animation(int timeOnScreen, BufferedImage... images){
		animationFrames = new LinkedList<>();
		this.timeOnScreen = timeOnScreen;
		frames = images.length - 1;
		for(BufferedImage img : images){
			animationFrames.add(img);
		}
		currentImg = animationFrames.get(0);
	}
	
	public void runAnimation(){
		currTime = System.currentTimeMillis();
		if(currTime - timeOfLastFrame > timeOnScreen){
			nextFrame();
			timeOfLastFrame = System.currentTimeMillis();
		}	
	}

	public void nextFrame(){
		if(count > frames){
			count = 1;
		}
		else{
			count++;
		}
		currentImg = animationFrames.get(count - 1);
	}
	
	public void drawAnimation(Graphics g, double x, double y, int offset){
		g.drawImage(currentImg, (int)x - offset, (int)y, null);
	}
	
	public void setCount(int count){
		this.count = count;
	}
	public int getCount(){
		return count;
	}
	
	public BufferedImage getCurrImage(){
		return currentImg;
	}
}