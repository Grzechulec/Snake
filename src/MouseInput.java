import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter{

private Handler handler;
	
	MouseInput(Handler handler){
		this.handler = handler;
	}
	
	private boolean checkPosition(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width && my > y && my < y + height) return true;
		else return false;
	}
	
	public void mouseClicked(MouseEvent e) {
		int mx = e.getX();
        int my = e.getY();
		if(e.getButton() == MouseEvent.BUTTON1 && checkPosition(mx, my, 460, 300, 120, 50))
			this.handler.getGame().startNewGame();
    }
}
