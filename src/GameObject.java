import java.awt.Graphics;
import java.util.ArrayList;

public abstract class GameObject {

	protected int x, y;
	protected ID id;
	protected int direction = 0;
	protected ArrayList<GameObject> tiles;
	
	public GameObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.tiles = new ArrayList<GameObject>();
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}	
	
}
