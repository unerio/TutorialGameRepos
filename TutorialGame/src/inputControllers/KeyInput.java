package inputControllers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import mainClasses.MainCanvas;

public class KeyInput extends KeyAdapter{
	
	private MainCanvas mc;
	
	public KeyInput(MainCanvas mc){
		this.mc = mc;
	}
	
	public void keyPressed(KeyEvent e){
		mc.keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e){
		mc.keyReleased(e);
	}
	
}
