package gameCharacters;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

//CLASSE CHE CREA L'OGGETTO ZENCHAN (NEMICO)
public class ZenChan extends DynamicObject {
	public final static int MOVE_RIGHT=0;
	public final static int MOVE_LEFT=1;
	
	private boolean dir;	//SE DIRECTION==0 ALLORA IL PLAYER SI MUOVE VERSO DESTRA, ALTRIMENTI VERSO SINISTRA
	private int speed;
	private Image img=null;
	private Image[] images=new Image[2];
	
	public ZenChan(int x, int y, int speed) {
		super(x, y);
		this.speed=speed;
		dir=true;
		
		try {
			images[MOVE_RIGHT] = ImageIO.read(new File("images/ZenChanR.png"));
			images[MOVE_LEFT] = ImageIO.read(new File("images/ZenChanL.png"));
			
			for (int i = 0; i < images.length; i++) 
				images[i] = images[i].getScaledInstance(40, 40, Image.SCALE_SMOOTH);
			
			img = images[MOVE_RIGHT];
		} catch (IOException e) {
				e.printStackTrace();
		}
		
	}
	
	public Image getImg() {
		return img;
	}
	public void setImg(Image img) {
		this.img = img;
	}
	
	public int getSpeed() {
		return speed;
	}

	public boolean isDir() {
		return dir;
	}

	public void setDir(boolean dir) {
		this.dir = dir;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void moveE()
	{
		if(dir) {
			setY(getY()+speed);
			img=images[MOVE_RIGHT];	
		}
		else {
			setY(getY()-speed);
			img=images[MOVE_LEFT];
		}
	}
}