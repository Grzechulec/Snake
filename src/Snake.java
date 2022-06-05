import java.awt.Graphics;

public class Snake extends GameObject{
	  // 1-down; 2-left; 3-up; 4-right
	private Handler handler;
	
	public Snake(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		direction = 2;
		this.handler = handler;
		this.tiles.add(new Tile(this.x, this.y, ID.PlayerHead));
		this.tiles.add(new Tile(this.x, this.y+1, ID.Body));
		this.tiles.add(new Tile(this.x, this.y+2, ID.Body));
		this.tiles.add(new Tile(this.x, this.y+3, ID.Body));
		this.tiles.add(new Tile(this.x, this.y+4, ID.Body));
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
	}

	public void render(Graphics g) {
		// TODO Auto-generated method stub
		for (GameObject object:tiles) {
            object.render(g);
        }
	}
	
	private GameObject collision() {
		for (GameObject object:this.handler.objects) {
			if(object.getX() == x && object.getY() == y && object.getId() != ID.Snake) {
				return object;
			}
			for (GameObject object2:object.tiles) {
				if(object2.getX() == x && object2.getY() == y && object2.getId() != ID.PlayerHead) {
					return object2;
				}
			}
		}
		return null; 
	}
	
	private void handleCollision(GameObject object) {
		if (this.getX() > 38 || this.getX() < 0) lose();
		else if (this.getY() > 27 || this.getY() < 0) lose();
		else if (object == null) return;
		else if (object.getId() == ID.Fruit) grow(object);
		else if (object.getId() == ID.Body) lose();
	}
	
	private void grow(GameObject object) {
		this.tiles.add(new Tile(this.x, this.y, ID.Body));
		handler.removeObject(object);
	}
	
	private void lose() {
		System.out.println("rip");
	}
	
	public int getDirection() {
		return this.direction;
	}
	
	public void setDireciton(int direction) {
		this.direction = direction;
	}
}
