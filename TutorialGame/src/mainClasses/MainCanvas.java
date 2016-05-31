package mainClasses;

import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import gameObjects.Player;
import inputControllers.KeyInput;
import inputControllers.MouseInput;
import spriteHandlers.BufferedImageLoader;
import spriteHandlers.Textures;

public class MainCanvas extends Canvas implements Runnable{

	private static final int WIDTH = 900;
	private static final int HEIGHT = 600;
	
	private boolean running = false;
	private Thread mainThread;

	private BufferedImage spriteSheet = null;
	private BufferedImage background;
	
	private Player player;
	private Controller gameObjectsController;
	private CollisionDetector collisionDetector;
	
	private double timeOfLastShot = 0;
	private double currTime = 0;
	private double recoveryTime = 400;
	
	private Textures images;
	
	private Cursor crosshair;
	
	public void init(){
		BufferedImageLoader loader = new BufferedImageLoader();
		spriteSheet = loader.loadImage("/mySpriteSheet1.png");
		background = loader.loadImage("/background.png");
		
		addKeyListener(new KeyInput(this));
		addMouseListener(new MouseInput(this));
		
		images = new Textures(spriteSheet);
		
		player = new Player(200.0, 400.0, images.getPlayerAnimation());
		
		gameObjectsController = new Controller(images.getBulletImage(),
										  images.getEnemyAnimation());
		
		collisionDetector = new CollisionDetector(gameObjectsController);
		
		crosshair = Toolkit.getDefaultToolkit().createCustomCursor(
								images.getCrosshairImage(), new Point(0, 0), "croshhair");
		
		setCursor(crosshair);
	}
	
	private synchronized void start(){
		if(!running){
			running = true;
			init();
			requestFocus();
			createBufferStrategy(3);
			mainThread = new Thread(this);
			mainThread.start();
		}
	}

	private synchronized void stop(){
		if(running){
			running = false;
			try {
				mainThread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.exit(1);
		}
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		final double AMOUNTOFTICKS = 60.0;
		double ns = 1000000000 / AMOUNTOFTICKS;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		while(running){
			long now = System.nanoTime();
			delta += (now -lastTime) / ns;
			lastTime = now;
			if(delta >= 1){
				tick();
				updates++;
				delta = 0;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer = System.currentTimeMillis();
				System.out.println("Ticks: " + updates + " Frames: " + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	private void tick(){
		player.tick();
		gameObjectsController.tick();
		collisionDetector.checkCollisions();
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(background, 0, 0, WIDTH, HEIGHT, this);
		
		player.render(g);
		gameObjectsController.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_W){
			player.setMovement("up", true);
		}
		if(key == KeyEvent.VK_A){
			player.setMovement("left", true);
		}
		if(key == KeyEvent.VK_S){
			player.setMovement("down", true);
		}
		if(key == KeyEvent.VK_D){
			player.setMovement("right", true);
		}
		if(key == KeyEvent.VK_E){
			gameObjectsController.addEnemy();
		}
		/*
		if(key == KeyEvent.VK_SPACE){
			currTime = System.currentTimeMillis();
			if(timeOfLastShot == 0 || currTime - timeOfLastShot > recoveryTime){
				player.shoot(gameObjectsController);
				timeOfLastShot = System.currentTimeMillis();
			}
		}
		*/
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_W){
			player.setMovement("up", false);
		}
		if(key == KeyEvent.VK_A){
			player.setMovement("left", false);
		}
		if(key == KeyEvent.VK_S){
			player.setMovement("down", false);
		}
		if(key == KeyEvent.VK_D){
			player.setMovement("right", false);
		}
	}
	
	public void mousePressed(MouseEvent e){
		if(e.getY() < player.getY()){
			player.shoot(gameObjectsController, e.getX(), e.getY());
		}
	}
	
	public static void main(String[] args) {
		MainCanvas mc = new MainCanvas();
		mc.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		JFrame f = new JFrame("Tutorial");
		
		f.add(mc);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setVisible(true);

		mc.start();
		
	}
	
}
