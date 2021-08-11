package gameObjects;

import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import gameCharacters.DynamicObject;

//CLASSE CHE CREA L'OGGETTO BANANA
public class Banana extends DynamicObject{
	private Image img=null;
	public Image getImg() {
		return img;
	}
	public void setImg(Image img) {
		this.img = img;
	}
	public Banana(int x, int y, boolean visible) {
		super(x, y);
		
		try {
			img=ImageIO.read(new File("images/banana.gif"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
