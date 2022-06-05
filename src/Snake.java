import java.awt.Graphics;
import java.util.ArrayList;

public class Snake extends GameObject{

	private ArrayList<Tile> tiles;
	private int direction;  // 1-down; 2-left; 3-up; 4-right
	
	public Snake(int x, int y, ID id) {
		super(x, y, id);
		tiles = new ArrayList<Tile>();
		direction = 2;
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
	}

	public void render(Graphics g) {
		// TODO Auto-generated method stub
		for (GameObject object:tiles) {
            object.render(g);
        }
	}
	
	public int getDirection() {
		return this.direction;
	}
	
	public void setDireciton(int direction) {
		this.direction = direction;
	}
}
