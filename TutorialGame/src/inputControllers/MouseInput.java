package inputControllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import mainClasses.MainCanvas;


public class MouseInput extends MouseAdapter{

	private MainCanvas mc;
	
	public MouseInput(MainCanvas mc){
		this.mc = mc;
	}

	@Override
	public void mousePressed(MouseEvent e){
		mc.mousePressed(e);
	}
	
}
