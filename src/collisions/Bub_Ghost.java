package collisions;

import gameCharacters.Ghost;
import gameCharacters.LittleDragon;
import manager.Matrix;

//CONTROLLO COLLISIONE TRA PLAYER E GHOST
public class Bub_Ghost {
	public boolean collision(Matrix m, LittleDragon bub, Ghost g) {
		if(m.getMatrix()[bub.getX()][bub.getY()].equals("2"))
		{
			m.getMatrix()[bub.getX()][bub.getY()]="0";
			return true;
		}
		
		return false;
	}
}
