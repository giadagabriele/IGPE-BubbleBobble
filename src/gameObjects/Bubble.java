package gameObjects;

import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import gameCharacters.DynamicObject;

//CLASSE CHE CREA L'OGGETTO BUBBLE
public class Bubble extends DynamicObject {

	private Image img=null;
	private int speed;
	private boolean dx;
	
	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Bubble(int x, int y, int speed, boolean visible, boolean dx) {
		super(x, y);
		this.speed=speed;
		this.dx=dx;
		
		try {
			img=ImageIO.read(new File("images/bubble.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isDx() {
		return dx;
	}

	public void setDx(boolean dx) {
		this.dx = dx;
	}
}
