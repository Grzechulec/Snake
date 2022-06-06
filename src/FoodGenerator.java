import java.util.Random;

public class FoodGenerator {
	private Handler handler;
	
	private int fruitAmount;
	private int frogAmount;
	private Random generator = new Random();
	
	FoodGenerator(Handler handler){
		this.handler = handler;
	}
	
	public void handleFood() {
		while (getFruitAmount() < 3) addFruit();
		while (getFrogAmount() < 1) addFrog();

	}
	
	private void addFruit() {
		int randX = generator.nextInt(25)+1;
		int randY = generator.nextInt(25)+1;
		handler.addObject(new Fruit(randX, randY, ID.Fruit));
	}
	
	private void addFrog() {
		int randX = generator.nextInt(25)+1;
		int randY = generator.nextInt(25)+1;
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
}
