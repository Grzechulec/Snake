import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
/**
 * Class describing a game
 * It contains all the essential parts of the game like for example a game loop
 */
public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = -1442798787354930462L;
	
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private FoodGenerator generator;
	private int highscore;
	private String file;
	public GameState gameState = GameState.Game;
	
	public Game(){
		this.handler = new Handler(this);
		this.generator = new FoodGenerator(this.handler);
		new Window(WIDTH, HEIGHT, "Snake", this);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput(handler));
		this.file = "src/aaa.txt";
		Path path = Path.of(file);
		String score;
		try {
			score = Files.readString(path);
			this.highscore = Integer.valueOf(score);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(gameState == GameState.Game) {
			handler.addObject(new Snake(20, 20, ID.Snake, ID.PlayerHead, handler));
			handler.addObject(new Enemy(5, 20, ID.EnemySnake, ID.EnemyHead, handler));
		}

		//generator.handleFood();
	}
	/**
	 * Method starting the game
	 */
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	/**
	 * Method stopping the game
	 */
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Game loop.
	 * It uses the delta time system which means that the performance of the PC
	 * won't affect the pace of the game.
	 * The game is set to be working in 5 ticks per second.
	 */
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTics = 5.0;
		double ns = 1000000000 / amountOfTics;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running) {
				render();
			}
			frames++;
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS= " + frames);
				frames = 0;
			}
		}
	}
	/**
	 * A method specifying actions which will take place in a tick
	 * In case that the game is in the Game GameState it will run the handleFood() and a tick() method of handler
	 * In case that the game is in GameOver State it will check if the high score was beaten
	 * and save it to the file
	 */
	private void tick() {
		if(gameState == GameState.Game) {
			generator.handleFood();
			this.handler.tick();
		}
		if(gameState == GameState.GameOver) {
			if (this.handler.length > highscore) {
				highscore = this.handler.length;
				try {
					FileWriter writer = new FileWriter("src/aaa.txt");
					PrintWriter print = new PrintWriter(writer);
					print.print(Integer.valueOf(highscore));
					print.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * A method specifying what to render every frame.
	 * It renders HUD when the game is in Game GameState and it renders GameOver screen in GameOver GameState
	 */
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		if (gameState == GameState.Game) {
			g.setColor(Color.darkGray);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.black);
			g.fillRect(16, 16, 16*25, 16*25);
			g.setColor(Color.white);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
			g.drawString("Score: " + Integer.toString(this.handler.length), 450, 50);
			g.drawString("Highscore: " + Integer.toString(this.highscore), 430, 150);
			g.setColor(Color.gray);
			g.fillRect(460, 300, 120, 50);
			g.setColor(Color.black);
			g.drawString("Reset", 480, 335);

		}
		this.handler.render(g);
		if (gameState == GameState.GameOver) {
			g.setColor(new Color(0, 0, 0, 150));
			g.fillRect(16, 16, 16*25, 16*25);
			g.setColor(Color.white);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 70));
			g.drawString("Game over", 40, 150);
			g.drawString("Score: " + Integer.toString(this.handler.length), 90, 250);
		}
				
		g.dispose();
		bs.show();
	}
	/**
	 * A method that starts the new game.
	 */
	public void startNewGame() {
		this.gameState = GameState.Game;
		while (!this.handler.objects.isEmpty()) {
			this.handler.removeObject(this.handler.objects.get(0));
		}
		handler.addObject(new Snake(20, 20, ID.Snake, ID.PlayerHead, handler));
		handler.addObject(new Enemy(5, 20, ID.EnemySnake, ID.EnemyHead, handler));
	}
	
	public static void main(String[] args) {
		new Game();
	}

}
