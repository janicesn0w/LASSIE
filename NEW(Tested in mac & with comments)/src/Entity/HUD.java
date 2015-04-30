package Entity;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class HUD implements ActionListener {
	
	private Font font = new Font("Comic Sans MS",Font.PLAIN,10);
	public static Timer timer;
	public static int sec;
	public static int time = sec;
	//set up the timer
	public HUD() {
		
		timer = new Timer(1000,this);
		timer.start();
		
		HUD.sec = 60;
		
	}
	//draw the timer onto the screen
	public void draw(Graphics2D g, Color color) {
		
		g.setFont(font);
		g.setColor(color);
		g.drawString("Time: "+0+":"+sec, 250, 10);
		
	}
	//ticking the timer
	public void actionPerformed(ActionEvent e) {
		time = sec;
		if (sec != 0) sec--;
		if (time == 0){
			timer.stop();
		}
	}

}
