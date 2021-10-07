package first_game;

import java.util.ArrayList;
import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Color;


public class Game extends Canvas implements Runnable{
	/**
	 * This is a personal study on game development, where the main objective is to 
	 * understand more about the complex algorithms used in the game industry.
	 * I chose java, as I already have some prior knowledge, as well as some reference
	 * books with example implementations using this language.
	 * 
	 * Throughout the implementation, I will try to use the best development and 
	 * documentation practices that the java community defines, avoiding as much
	 * as possible to bring vices from other languages ​​I work with.
	 * 
	 * @author Cristian Paixão
	 */
	private static final long serialVersionUID = 1L;
	private boolean isRunning;
	private Thread thread;
	private ArrayList<Entity> entities = new ArrayList<>();
	public static JFrame frame;
	private final int WIDTH = 160;
	private final int HEIGHT = 120;
	private final int SCALE = 3;

	private BufferedImage image;
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		initFrame();
		image = new BufferedImage(160, 120, BufferedImage.TYPE_INT_RGB);

		//entities.add(null);
	}
	
	public void initFrame() {
		frame = new JFrame("Game Title");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
		
	}
	
	public synchronized void start() {
		// Initialize the game threading
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		// Stop the game threading
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void tick() {
		// Update the game
//		System.out.println("Tick");
	}
	
	public void render() {
		// Render the game
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = image.getGraphics();
		g.setColor(new Color(250,19,19));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		bs.show();
	}

	@Override
	public void run() {
		/**
		 * Game loop implementation.
		 * 
		 * Here, we process the entire logic layer of the game, 
		 * as well as set the frame-per-second refresh rate.
		 */
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.00;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer += 1000;
			}
		}
	
		stop();
	}
}
