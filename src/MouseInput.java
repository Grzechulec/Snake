import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * This class handles all the mouse inputs
 *
 */
public class MouseInput extends MouseAdapter{

private Handler handler;
	
	MouseInput(Handler handler){
		this.handler = handler;
	}
	/**
	 * This method checks if the mouse is inside a box
	 * @param mx mouse X
	 * @param my mouse Y
	 * @param x box X
	 * @param y box Y
	 * @param width box width
	 * @param height box height
	 * @return true if it is inside
	 */
	private boolean checkPosition(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width && my > y && my < y + height) return true;
		else return false;
	}
	/**
	 * Method restarts the game if the restart button was pressed
	 */
	public void mouseClicked(MouseEvent e) {
		int mx = e.getX();
        int my = e.getY();
		if(e.getButton() == MouseEvent.BUTTON1 && checkPosition(mx, my, 460, 300, 120, 50))
			this.handler.getGame().startNewGame();
    }
}
