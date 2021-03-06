import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 * This class handles all the keyboard inputs
 *
 */
public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	
	KeyInput(Handler handler){
		this.handler = handler;
	}
	/**
	 * This method runs only when the key is pressed, then it runs certain action depending on the key which was pressed.
	 * It implements snake move by WASD keys and game reset by R key
	 */
	public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
		if (k == KeyEvent.VK_W || k == KeyEvent.VK_A || k == KeyEvent.VK_S || k == KeyEvent.VK_D ) {
			for (int i=0; i<handler.objects.size(); i++) {
				if (handler.objects.get(i).id == ID.Snake) {
					GameObject snake = handler.objects.get(i);
					if(k == KeyEvent.VK_W && snake.getPreviousDirection() != 1) snake.setDirection(3);
					if(k == KeyEvent.VK_A && snake.getPreviousDirection() != 4) snake.setDirection(2);
					if(k == KeyEvent.VK_S && snake.getPreviousDirection() != 3) snake.setDirection(1);
					if(k == KeyEvent.VK_D && snake.getPreviousDirection() != 2) snake.setDirection(4);
				}
			}
		}
		if (k == KeyEvent.VK_R) this.handler.getGame().startNewGame();
		//if (k == KeyEvent.VK)
	}
}
