import java.awt.Color;
import java.awt.Graphics;

public class Tile extends GameObject {

	public Tile(int x, int y) {
		super(x, y);
	}

	public void tick() {
		
		
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 16, 16);
	}

}
