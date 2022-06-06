import java.awt.Color;
import java.awt.Graphics;

public class Tile extends GameObject {

	final int tileSize = 16;
	
	public Tile(int x, int y, ID id) {
		super(x, y, id);
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		if (this.id == ID.Body) g.setColor(Color.white);
		else if (this.id == ID.PlayerHead)g.setColor(Color.green);
		else if (this.id == ID.EnemyHead)g.setColor(Color.red);
		g.fillRect(x*tileSize, y*tileSize, tileSize, tileSize);
	}

}
