import java.awt.Color;
import java.awt.Graphics;

/**
 * This class describes the Snake object.
 * Snake consists of the Tiles which follow him
 */
public class Snake extends GameObject{
	  // 1-down; 2-left; 3-up; 4-right
	private Handler handler;
	private Color c;
	
	public Snake(int x, int y, ID id, ID player, Handler handler) {
		super(x, y, id);
		direction = 3;
		c = new Color(0, 180, 0);
		if (player == ID.EnemyHead) c = new Color(180, 0, 0);
		this.handler = handler;
		this.tiles.add(new Tile(this.x, this.y, player));
		this.tiles.add(new Tile(this.x, this.y+1, ID.Body, c));
		this.tiles.add(new Tile(this.x, this.y+2, ID.Body, c));
		this.tiles.add(new Tile(this.x, this.y+3, ID.Body, c));
		this.tiles.add(new Tile(this.x, this.y+4, ID.Body, c));
	}
	/**
	 * This method makes the snake move in the set direction and it moves its tiles
	 */
	public void tick() {
		for (int i=tiles.size()-1; i > 0; i--) {
			this.tiles.get(i).setX(this.tiles.get(i-1).getX());
			this.tiles.get(i).setY(this.tiles.get(i-1).getY());
		}
		switch(this.direction) {
		case 1:
			this.tiles.get(0).setY(this.tiles.get(0).getY()+1);
			break;

		case 2:
			this.tiles.get(0).setX(this.tiles.get(0).getX()-1);
			break;
		
		case 3:
			this.tiles.get(0).setY(this.tiles.get(0).getY()-1);
			break;

		case 4:
			this.tiles.get(0).setX(this.tiles.get(0).getX()+1);
			break;
		}
		this.x = this.tiles.get(0).getX();
		this.y = this.tiles.get(0).getY();
		handleCollision(collision());
		this.previousDirection = this.getDirection();
		if (this.getId() == ID.Snake) this.handler.length = this.tiles.size();
	}

	public void render(Graphics g) {
		// TODO Auto-generated method stub
		for (GameObject object:tiles) {
            object.render(g);
        }
	}
	/**
	 * This method detects the collision of the snake
	 * @return object that snake collided with
	 */
	private GameObject collision() {
		for (GameObject object:this.handler.objects) {
			if(object.getX() == this.getX() && object.getY() == this.getY() && object.getId() != ID.Snake && object.getId() != ID.EnemySnake) {
				return object;
			}
			for (GameObject object2:object.tiles) {
				if(object2.getX() == x && object2.getY() == y && object2.getId() == ID.Body) {
					return object2;
				}
			}
		}
		return null; 
	}
	/**
	 * This method handles what effect will collision have (depending on object it collided with)
	 * @param object object snake collided with
	 */
	private void handleCollision(GameObject object) {
		if (this.getX() > 25 || this.getX() < 1) lose();
		else if (this.getY() > 25 || this.getY() < 1) lose();
		else if (object == null) return;
		else if (object.getId() == ID.Fruit) grow(object);
		else if (object.getId() == ID.Frog) grow(object);
		else if (object.getId() == ID.Body) lose();
	}
	/**
	 * Method makes snake grow and removes certain food
	 * This method is called on collision with food
	 * @param object GameObject snake collided with
	 */
	private void grow(GameObject object) {
		this.tiles.add(new Tile(-1, -1, ID.Body, c));
		handler.removeObject(object);
	}
	
	/**
	 * This method calls the GameOver screen
	 * It is called on Snake collision with Tile or with wall.
	 */
	private void lose() {
		System.out.println("rip");
		if (this.getId() == ID.EnemySnake)
			this.handler.removeObject(this);
		else
			this.handler.getGame().gameState = GameState.GameOver;
	}

}
