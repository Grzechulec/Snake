import java.util.Random;

public class Enemy extends Snake {

	private Handler handler;
	private Random generator;
	
	public Enemy(int x, int y, ID id, ID player, Handler handler) {
		super(x, y, id, player, handler);
		// TODO Auto-generated constructor stub
		this.handler = handler;
		this.generator = new Random();
	}
	public void tick() {
		ai();
		super.tick();
	}
	
	private void ai() {
		GameObject fruit = findFruit();
		this.setDirection(directionToFruit(fruit));
		int side = generator.nextInt(1) * 2 - 1;
		if(!directionValidation()) direction += side;
		if(direction == 5) direction = 1;
		if(direction == 0) direction = 4;
		if(!directionValidation()) direction += side;
		if(direction == 5) direction = 1;
		if(direction == 0) direction = 4;
		if(!directionValidation()) direction += side;
		if(direction == 5) direction = 1;
		if(direction == 0) direction = 4;
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
			if (object.getId() == ID.Fruit && getDistanceFromFruit(object) < distance) { 
				outputFruit = object;
				distance = getDistanceFromFruit(object);
			}
		}
		return outputFruit;
	}
	
	private float getDistanceFromFruit(GameObject fruit) {
		return Math.abs(this.getX() - fruit.getX())+Math.abs(this.getY() - fruit.getY());
	}
	
	private boolean directionValidation() {
		if (getDirection() == 1 && getDistanceDown() == 1) return false;
		else if (getDirection() == 2 && getDistanceLeft() == 1) return false;
		else if (getDirection() == 3 && getDistanceUp() == 1) return false;
		else if (getDirection() == 4 && getDistanceRight() == 1) return false;
		else return true;
	}
	
	private int getDistanceDown() {
		int distance = Math.abs(26 - this.y);
		for(GameObject object:this.handler.objects) {
			if (object.getX() == this.x && object.getId() != ID.Frog && this.y < object.getY() && object.getId() != ID.Fruit) {
				distance = Math.min(distance, Math.abs(this.y - object.getY()));
			}
			for(GameObject tile:object.tiles) {
				if (tile.getX() == this.x && this.y < tile.getY()) {
					distance = Math.min(distance, Math.abs(this.y - tile.getY()));
				}
			}
		}	
		return distance;
	}
	
	private int getDistanceUp() {
		int distance = Math.abs(this.y);
		for(GameObject object:this.handler.objects) {
			if (object.getX() == this.x && object.getId() != ID.Frog && this.y > object.getY() && object.getId() != ID.Fruit) {
				distance = Math.min(distance, Math.abs(this.y - object.getY()));
			}
			for(GameObject tile:object.tiles) {
				if (tile.getX() == this.x && this.y > tile.getY()) {
					distance = Math.min(distance, Math.abs(this.y - tile.getY()));
				}
			}
		}	
		return distance;
	}
	
	private int getDistanceLeft() {
		int distance = Math.abs(this.x);
		for(GameObject object:this.handler.objects) {
			if (object.getY() == this.y && object.getId() != ID.Frog && this.x > object.getX() && object.getId() != ID.Fruit) {
				distance = Math.min(distance, Math.abs(this.x - object.getX()));
			}
			for(GameObject tile:object.tiles) {
				if (tile.getY() == this.y && this.x > tile.getX()) {
					distance = Math.min(distance, Math.abs(this.x - tile.getX()));
				}
			}
		}	
		return distance;
	}
	
	private int getDistanceRight() {
		int distance = Math.abs(this.x - 26);
		for(GameObject object:this.handler.objects) {
			if (object.getY() == this.y && object.getId() != ID.Frog && this.x < object.getX() && object.getId() != ID.Fruit) {
				distance = Math.min(distance, Math.abs(this.x - object.getX()));
			}
			for(GameObject tile:object.tiles) {
				if (tile.getY() == this.y && this.x < tile.getX()) {
					distance = Math.min(distance, Math.abs(this.x - tile.getX()));
				}
			}
		}	
		return distance;
	}
}
