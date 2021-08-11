package collisions;

import gameCharacters.LittleDragon;
import gameCharacters.ZenChan;
import manager.Matrix;

//CONTROLLO COLLISIONE PLAYER E NEMICO
public class Bub_ZenChan {
	public boolean collision(Matrix m, LittleDragon bub, ZenChan z) {
		if(m.getMatrix()[bub.getX()][bub.getY()].equals("3"))
		{
			m.getMatrix()[bub.getX()][bub.getY()]="0";
			return true;
		}
		
		return false;
	}
}
