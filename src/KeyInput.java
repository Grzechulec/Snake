import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	
	KeyInput(Handler handler){
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
		if (k == KeyEvent.VK_W || k == KeyEvent.VK_A || k == KeyEvent.VK_S || k == KeyEvent.VK_D ) {
			for (int i=0; i<handler.objects.size(); i++) {
				if (handler.objects.get(i).id == ID.Snake) {
					if(k == KeyEvent.VK_W) handler.objects.get(i).setDirection(3);
					if(k == KeyEvent.VK_A) handler.objects.get(i).setDirection(2);
					if(k == KeyEvent.VK_S) handler.objects.get(i).setDirection(1);
					if(k == KeyEvent.VK_D) handler.objects.get(i).setDirection(4);
				}
			}
		}
	}
}
