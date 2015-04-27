package Main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import GameStates.GameStateManager;


@SuppressWarnings("serial")
public class GameLoop extends JPanel implements Runnable , KeyListener, ActionListener{

	private Thread thread;
	private boolean running;
			
	private int fps = 60;
	private long targetTime = 1000 / fps;
	
	private Graphics2D g;
	private BufferedImage img;

	private GameStateManager gsm;
	//WindowSize
	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
	public static final int SCALE = 2;
	
	
	public GameLoop()
	{
		super();
		setFocusable(true);
		requestFocus();
	}
	
	@Override
	public void addNotify() 
	{
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			
			thread.start();
		}
	}
	
	private void init() 
	{
		img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) img.getGraphics();
		running = true;
		gsm = new GameStateManager();
	}
	
	@Override
	public void run() 
	{
		init();
		
		long start;
		long deltatime;
		long wait;
		
		while (running)
		{
			start = System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			
			deltatime = System.nanoTime() - start;
			wait = targetTime - deltatime / 1000000;
			
			if (wait < 0)	wait = 5;
			
			try{
				Thread.sleep(wait);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
	}

	private void update() 
	{
		gsm.update();
	}
	
	private void drawToScreen() 
	{
		Graphics g2 = getGraphics();
		g2.drawImage(img, 0, 0,	WIDTH * SCALE, HEIGHT * SCALE, null);
		g2.dispose();
	}

	private void draw() 
	{
		gsm.draw(g);
	}

	
	@Override
	public void keyPressed(KeyEvent e)
	{
		gsm.keyPressed(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		gsm.keyReleased(e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e){}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
