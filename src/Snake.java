import java.awt.Color;
import java.awt.Graphics;

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
	}

	public void render(Graphics g) {
		// TODO Auto-generated method stub
		for (GameObject object:tiles) {
            object.render(g);
        }
	}
	
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
	
	private void handleCollision(GameObject object) {
		if (this.getX() > 25 || this.getX() < 1) lose();
		else if (this.getY() > 25 || this.getY() < 1) lose();
		else if (object == null) return;
		else if (object.getId() == ID.Fruit) grow(object);
		else if (object.getId() == ID.Frog) grow(object);
		else if (object.getId() == ID.Body) lose();
	}
	
	private void grow(GameObject object) {
		this.tiles.add(new Tile(-1, -1, ID.Body, c));
		handler.removeObject(object);
	}
	
	private void lose() {
		System.out.println("rip");
	}

}
