package gameCharacters;

//CLASSE BASE DAL QUALE EREDITANO LittleDragon, ZenChan e Ghost
public class DynamicObject {
	private int x;
	private int y;
	private boolean visible;
		
	public DynamicObject(int x, int y) {
		this.x=x;
		this.y=y;
		this.visible=true;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public int getX() {
		return x;
	}
 
	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}	
}
