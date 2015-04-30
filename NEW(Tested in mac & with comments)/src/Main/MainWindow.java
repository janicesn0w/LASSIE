package Main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MainWindow{
	
	public static JFrame window;
	//open a window
	public static void main(String args[])
	{
	window = new JFrame ("Lassie Come Home");
	window.setContentPane(new GameLoop());
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	window.setSize(GameLoop.WIDTH * GameLoop.SCALE,GameLoop.HEIGHT * GameLoop.SCALE);
	window.setVisible(true);
	window.setResizable(false);
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	window.setLocation(screen.width/2 - GameLoop.WIDTH, screen.height/2 - GameLoop.HEIGHT);
	}
}
