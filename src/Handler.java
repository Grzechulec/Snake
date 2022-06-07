import java.awt.Graphics;
import java.util.ArrayList;

public class Handler {
	
	private Game game;
	ArrayList<GameObject> objects = new ArrayList<GameObject>();
	//private FoodGenerator generator = new
	public Handler(Game game) {
		this.game = game;
	}
	
	public void tick() {
//		for (GameObject object:objects) {
//            object.tick();
//        }
		for (int i=0; i<objects.size(); i++) {
			objects.get(i).tick();
		}
	}
	
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

