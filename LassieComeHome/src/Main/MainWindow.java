package Main;

import javax.swing.JFrame;

public class MainWindow{
	
	public static void main(String args[])
	{
	JFrame window = new JFrame ("Lassie Come Home");
	window.setContentPane(new GameLoop());
	window.pack();
	window.setSize(GameLoop.WIDTH * GameLoop.SCALE,GameLoop.HEIGHT * GameLoop.SCALE);
	window.setVisible(true);
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	window.setResizable(false);
	
	}
}
