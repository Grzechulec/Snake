import java.awt.Graphics;
import java.util.ArrayList;
/**
 * The class meant to handle all the objects in the game
 *
 */
public class Handler {
	
	private Game game;
	ArrayList<GameObject> objects = new ArrayList<GameObject>();
	public int length;
	public Handler(Game game) {
		this.game = game;
	}
	/**
	 * This method runs every tick of the game and it makes all the GameObjects to run their tick() method
	 */
	public void tick() {
		for (int i=0; i<objects.size(); i++) {
			objects.get(i).tick();
		}
	}
	/**
	 * This method runs every frame of the game and it makes all the GameObjects to run their render() method
	 * @param g
	 */
	public void render(Graphics g) {
		for (GameObject object:objects) {
            object.render(g);
        }
	}
	
	public void addObject(GameObject object) {
		this.objects.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.objects.removeIf(n -> n == object);
	}

	public Game getGame() {
		return this.game;
	}
}

