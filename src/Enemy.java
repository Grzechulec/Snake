
public class Enemy extends Snake {

	private Handler handler;

	
	public Enemy(int x, int y, ID id, ID player, Handler handler) {
		super(x, y, id, player, handler);
		// TODO Auto-generated constructor stub
		this.handler = handler;
	}
	public void tick() {
		ai();
		super.tick();
	}
	
	private void ai() {
		GameObject fruit = findFruit();
		this.setDirection(directionToFruit(fruit));
	}
	
	private int directionToFruit(GameObject fruit) {
		int outputDirection = 1;
		if (fruit.getX() > this.getX()) outputDirection = 4; 
		else if (fruit.getX() < this.getX()) outputDirection = 2;
		else {
			if (fruit.getY() > this.getY()) outputDirection = 1;
			else if (fruit.getY() < this.getY()) outputDirection = 3;
		}
		return outputDirection;
	}
	
	private GameObject findFruit() {
		float distance = 999.0f;
		GameObject outputFruit = new Tile(100, 100, ID.Body);
		for(GameObject object:this.handler.objects) {
			if (object.getId() == ID.Fruit && getDistance(object) < distance) { 
				outputFruit = object;
				distance = getDistance(object);
			}
		}
		return outputFruit;
	}
	
	private float getDistance(GameObject fruit) {
		return Math.abs(this.getX() - fruit.getX())+Math.abs(this.getY() - fruit.getY());
	}
}
