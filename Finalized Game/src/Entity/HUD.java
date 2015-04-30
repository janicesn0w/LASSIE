package Entity;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class HUD implements ActionListener {
	
	private Font font;
	public static Timer timer;
	public static int sec;
	public static int time = sec;
	
	public HUD() {
		timer = new Timer(1000,this);
		timer.start();
		
	}
	
	public void draw(Graphics2D g, Color color) {
		
		g.setFont(font);
		g.setColor(color);
		g.drawString("P: Pause", 5, 10);
		g.drawString("Time: " +sec, 250, 10);
		
	}

	public void actionPerformed(ActionEvent e) {
		time = sec;
		if (sec != 0) sec--;
		if (time == 0){
			timer.stop();
		}
	}

}
