package Entity;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class HUD implements ActionListener {
	
	private Font font;
	public static Timer timer;
	
	private static int min;
	private static int sec;
	public static int time = min + sec;
	
	public HUD(Dog p) {
		timer = new Timer(1000,this);
		timer.start();
		
		HUD.min = 0;
		HUD.sec = 5;
	}
	
	public void draw(Graphics2D g) {
		
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawString("Time: "+min+":"+sec, 250, 10);
		
	}

	public void actionPerformed(ActionEvent e) {
		time = min + sec;
		if (min != 0 && sec == 0){
			min--;
			sec = 59;
		}
		else if (sec != 0) sec--;
		if (time == 0){
			timer.stop();
		}
	}

}
