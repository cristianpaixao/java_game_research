package first_game;

import java.util.ArrayList;
import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;

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
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		frame = new JFrame("Game Title");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		//entities.add(null);
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

	public void tick() {
		// Update the game
		System.out.println("Tick");
	}
	
	public void render() {
		// Render the game
		System.out.println("Render");
	}

	@Override
	public void run() {
		/**
		 * Game loop implementation.
		 * 
		 * Here, we process the entire logic layer of the game, 
		 * as well as set the frame-per-second refresh rate.
		 */
		while (isRunning) {
			tick();
			render();
		}
		
	}
}
