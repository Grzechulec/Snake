import java.awt.Color;
import java.awt.Graphics;

public class Frog extends GameObject{

	final int interval = 3;
	private int count = 0;
	
	public Frog(int x, int y, ID id) {
		super(x, y, id);
	}

	public void tick() {
		count++;
		if (count == interval) {
			this.y = this.y - 1;
			count = 0;
		}
	}

	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.GREEN);
		g.fillRect(x*16, y*16, 16, 16);
	}

}
