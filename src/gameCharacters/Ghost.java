package gameCharacters;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

//CLASSE CHE CREA L'OGGETTO GHOST (NEMICO SPECIALE)
public class Ghost extends DynamicObject {
	public final static int MOVE_RIGHT=0;
	public final static int MOVE_LEFT=1;
	public final static int MOVE_UP=2;
	public final static int MOVE_DOWN=3;
	
	private boolean dirRow;	//VERIFICA SE E' POSSIBILE MUOVERSI LUNGO LA RIGA
	private boolean dirColumn;	//VERIFICA SE E' POSSIBILE MUOVERSI LUNGO LA COLONNA
	private int speed;
	private Image img=null;
	public Image[] images=new Image[2];
	
	public Ghost(int x, int y, int speed, boolean visible) {
		super(x, y);
		this.speed=speed;
		dirRow=true;
		dirColumn=true;
		
		try {
			images[MOVE_RIGHT] = ImageIO.read(new File("images/ghostR.png"));
			images[MOVE_LEFT] = ImageIO.read(new File("images/ghostL.png"));
			
			for (int i = 0; i < images.length; i++) 
				images[i] = images[i].getScaledInstance(40, 40, Image.SCALE_SMOOTH);
			
			img = images[MOVE_RIGHT];
		} catch (IOException e) {
				e.printStackTrace();
		}
	}
	
	public boolean isDirRow() {
		return dirRow;
	}

	public void setDirRow(boolean dirRow) {
		this.dirRow = dirRow;
	}

	public boolean isDirColumn() {
		return dirColumn;
	}

	public void setDirColumn(boolean dirColumn) {
		this.dirColumn = dirColumn;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public void moveG()
	{
		if(dirRow) {
			setY(getY()+speed);
			img=images[MOVE_RIGHT];	
		}
		
		else {
			setY(getY()-speed);
			img=images[MOVE_LEFT];
		}
	}
}
