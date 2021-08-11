package collisions;

import gameObjects.Bubble;
import manager.Matrix;

//CONTROLLO COLLISIONE TRA BOLLA E NEMICO
public class Bubble_ZenChan {
	
	public boolean collision(Matrix m, Bubble b) {
		if(m.getMatrix()[b.getX()][b.getY()].equals("3") || m.getMatrix()[b.getX()][b.getY()].equals("2")) {
			b.setVisible(false);
			return true;
		}
		
		return false;
	}
}
