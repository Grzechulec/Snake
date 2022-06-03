import java.awt.Color;
import java.awt.Graphics;

public class Tile extends GameObject {

	final int tileSize = 16;
	
	public Tile(int x, int y) {
		super(x, y);
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x*tileSize, y*tileSize, tileSize, tileSize);
	}

}
