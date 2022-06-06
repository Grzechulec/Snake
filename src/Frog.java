import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class Frog extends GameObject{

	final int interval = 3;
	private int count = 0;
	private Handler handler;
	
	public Frog(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	public void tick() {
		count++;
		if (count == interval) {
			ai();
			switch(this.direction) {
			case 1:
				this.setY(this.getY()+1);
				break;

			case 2:
				this.setX(this.getX()-1);
				break;
			
			case 3:
				this.setY(this.getY()-1);
				break;

			case 4:
				this.setX(this.getX()+1);
				break;
			}
			count = 0;
		}
	}

	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.GREEN);
		g.fillRect(x*16, y*16, 16, 16);
	}
	
	private int getDistanceDown() {
		int distance = Math.abs(25 - this.y);
		for(GameObject object:this.handler.objects) {
			if (object.getX() == this.x && object.getId() != ID.Frog && this.y < object.getY()) {
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
		int distance = Math.abs(this.y - 1);
		for(GameObject object:this.handler.objects) {
			if (object.getX() == this.x && object.getId() != ID.Frog && this.y > object.getY()) {
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
		int distance = Math.abs(this.x - 1);
		for(GameObject object:this.handler.objects) {
			if (object.getY() == this.y && object.getId() != ID.Frog && this.x > object.getX()) {
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
		int distance = Math.abs(this.x - 25);
		for(GameObject object:this.handler.objects) {
			if (object.getY() == this.y && object.getId() != ID.Frog && this.x < object.getX()) {
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
	
	private void ai() {
		int min_value = 1000;
		LinkedList<Integer> distances = new LinkedList<Integer>();
		distances.add(getDistanceUp());
		distances.add(getDistanceRight());
		distances.add(getDistanceDown());
		distances.add(getDistanceLeft());
		System.out.println(getDistanceUp());
		for(int i = 0; i < 4; i++) {
			if (distances.get(i) < min_value) {
				min_value = distances.get(i);
				this.setDirection(i+1);
			}
		}
	}
}
