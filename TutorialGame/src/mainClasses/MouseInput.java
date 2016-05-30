package mainClasses;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MouseInput extends MouseAdapter{

	private MainCanvas mc;
	
	public MouseInput(MainCanvas mc){
		this.mc = mc;
	}

	@Override
	public void mouseClicked(MouseEvent e){
		mc.mouseClicked(e);
	}
	
}
