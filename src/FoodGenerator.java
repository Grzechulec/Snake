import java.util.Random;
/**
 * The purpose of this class is to generate food on the map.
 * There should be always 3 fruits and 1 frog on the map.
 * The food shouldn't overlap
 */
public class FoodGenerator {
	private Handler handler;
	
	private int fruitAmount;
	private int frogAmount;
	private Random generator = new Random();
	
	FoodGenerator(Handler handler){
		this.handler = handler;
	}
	/**
	 * This method generates food when there's lack of it
	 */
	public void handleFood() {
		while (getFruitAmount() < 3) addFruit();
		while (getFrogAmount() < 1) addFrog();

	}
	/**
	 * This method adds fruit on random unoccupied coordinates
	 */
	private void addFruit() {
		int randX = generator.nextInt(25)+1;
		int randY = generator.nextInt(25)+1;
		while(!spaceValidation(randX, randY)) {
			randX = generator.nextInt(25)+1;
			randY = generator.nextInt(25)+1;
		}
		handler.addObject(new Fruit(randX, randY, ID.Fruit));
	}
	/**
	 * This method adds frog on random unoccupied coordinates
	 */
	private void addFrog() {
		int randX = generator.nextInt(25)+1;
		int randY = generator.nextInt(25)+1;
		while(!spaceValidation(randX, randY)) {
			randX = generator.nextInt(25)+1;
			randY = generator.nextInt(25)+1;
		}
		handler.addObject(new Frog(randX, randY, ID.Frog, handler));
	}

	private int getFruitAmount() {
		fruitAmount = 0;
		for (GameObject object:handler.objects) {
			if(object.getId() == ID.Fruit) fruitAmount++;
		}
		return fruitAmount;
	}
	
	private int getFrogAmount() {
		frogAmount = 0;
		for (GameObject object:handler.objects) {
			if(object.getId() == ID.Frog) frogAmount++;
		}
		return frogAmount;
	}
	/**
	 * This method checks if specified coordinates are occupied
	 * @param randX coordinate X
	 * @param randY coordinate Y
	 * @return true if not occupied
	 */
	private boolean spaceValidation(int randX, int randY) {
		for (GameObject object:handler.objects) {
			if (object.getX() == randX && object.getY() == randY) {
				return false;
			}
			for(GameObject tile:object.tiles) {
				if (tile.getX() == randX && tile.getY() == randY) {
					return false;
				}
			}
		}
		return true;
	}
}
