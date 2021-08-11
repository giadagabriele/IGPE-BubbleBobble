package gameObjects;

import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import gameCharacters.DynamicObject;

//CLASSE CHE CREA L'OGGETTO APPLE
public class Apple extends DynamicObject {
	private Image img=null;
	
	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public Apple(int x, int y, boolean visible) {
		super(x, y);
		
		try {
			img=ImageIO.read(new File("images/apple.gif"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
