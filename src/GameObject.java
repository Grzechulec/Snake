import java.awt.Graphics;

public abstract class GameObject {

	protected int x, y;
	
	public GameObject(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
}
