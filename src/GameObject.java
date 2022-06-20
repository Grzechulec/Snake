import java.awt.Graphics;
import java.util.ArrayList;
/**
 * An abstract class that describes Game Object
 * Direction variable describes in which direction the object will go in the next tick:
 * 0=none; 1=down; 2=left; 3=up; 4=right;
 */
public abstract class GameObject {

	protected int x, y;
	protected ID id;
	protected int direction = 0;
	protected ArrayList<GameObject> tiles;
	protected int previousDirection = 0;
	
	public GameObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.tiles = new ArrayList<GameObject>();
	}
	/**
	 * This method describes actions that will happen every tick of the game
	 */
	public abstract void tick();
	/**
	 * This method describes what will be rendered on the screen every frame of the game
	 * @param g
	 */
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
	
	public int getPreviousDirection() {
		return previousDirection;
	}
	
}
