import java.awt.Color;
import java.awt.Graphics;

public class Fruit extends GameObject{

	final int tileSize = 16;
	
	public Fruit(int x, int y, ID id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.red);
		g.fillRect(x*tileSize, y*tileSize, tileSize, tileSize);
	}

}
