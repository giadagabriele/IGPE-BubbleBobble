package gameCharacters;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

//CLASSE CHE CREA L'OGGETTO LITTLEDRAGON (PLAYER)
public class LittleDragon extends DynamicObject {
	public final static int MOVE_UP=2;
	public final static int MOVE_RIGHT=0;
	public final static int MOVE_LEFT=1;
	
	private int speed;
	private int life;
	private int direction;	//SE DIRECTION==0 ALLORA IL PLAYER SI MUOVE VERSO DESTRA, ALTRIMENTI VERSO SINISTRA
	private int score;
	private Image img=null;
	private Image[] images=new Image[2];
	private boolean up;
	private int cont;	//CONTATORE CHE VIENE UTILIZZATO PER VERIFICARE L'ALTEZZA MASSIMA DEL SALTO
	
	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public LittleDragon(int x, int y, int speed, int life, int score) {
		super(x, y);
		this.speed=speed;
		this.life=life;
		this.score=score;
		direction=0;
		up=false;
		cont=0;
		
		try {
			images[MOVE_RIGHT] = ImageIO.read(new File("images/BubR.png"));
			images[MOVE_LEFT] = ImageIO.read(new File("images/BubL.png"));
			
			for (int i = 0; i < images.length; i++) 
				images[i] = images[i].getScaledInstance(40, 40, Image.SCALE_SMOOTH);
			
			img = images[MOVE_RIGHT];
		} catch (IOException e) {
				e.printStackTrace();
		}
	}
	
	public int getCont() {
		return cont;
	}

	public void setCont(int cont) {
		this.cont = cont;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void move(int direction)
	{
		switch (direction) {
		case MOVE_UP:
			setX(getX()-speed);
			break;	
		case MOVE_RIGHT:
			setY(getY()+speed);
			img=images[direction]; 
			setDirection(0);
			break;
		case MOVE_LEFT:
			setY(getY()-speed);
			img=images[direction];
			setDirection(1);
			break;
		default:
			break;
		}
	}
	
	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}