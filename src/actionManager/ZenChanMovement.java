package actionManager;

import gameCharacters.ZenChan;
import manager.Matrix;

//GESTORE MOVIMENTO NEMICO STANDARD
public class ZenChanMovement {
	
	//CONTROLLA SE E' POSSIBILE MUOVERSI VERSO DESTRA
	public void movementR(Matrix m, ZenChan z) {
		//SI MUOVE SE DAVANTI A SE HA IL VUOTO OPPURE IL PLAYER
		if(m.getMatrix()[z.getX()][z.getY()+1].equals("0") || m.getMatrix()[z.getX()][z.getY()+1].equals("5")) {
			m.getMatrix()[z.getX()][z.getY()]="0";
			z.moveE();
			m.getMatrix()[z.getX()][z.getY()]="3";
		}
		
		//SE INCONTRA UN OGGETTO CHE PUO' ESSERE RACCOLTO, LO OLTREPASSA
		else if(((m.getMatrix()[z.getX()][z.getY()+1].equals("6")) || (m.getMatrix()[z.getX()][z.getY()+1].equals("7")) 
				|| (m.getMatrix()[z.getX()][z.getY()+1].equals("8")) || (m.getMatrix()[z.getX()][z.getY()+1].equals("2")))
				&& !m.getMatrix()[z.getX()][z.getY()+2].equals("1")) {
			m.getMatrix()[z.getX()][z.getY()]="0";
			z.setY(z.getY()+2);
			m.getMatrix()[z.getX()][z.getY()]="3";
		}
		
		//NEGA IL MOVIMENTO
		else
			z.setDir(false);
	}

	//CONTROLLA SE E' POSSIBILE MUOVERSI VERSO SINISTRA
	public void movementL(Matrix m, ZenChan z) {
		//SI MUOVE SE DAVANTI A SE HA IL VUOTO OPPURE IL PLAYER
		if (m.getMatrix()[z.getX()][z.getY()-1].equals("0") || m.getMatrix()[z.getX()][z.getY()-1].equals("5")) {
			m.getMatrix()[z.getX()][z.getY()]="0";
			z.moveE();
			m.getMatrix()[z.getX()][z.getY()]="3";
		}	
		
		//SE INCONTRA UN OGGETTO CHE PUO' ESSERE RACCOLTO, LO OLTREPASSA
		else if(((m.getMatrix()[z.getX()][z.getY()-1].equals("6")) || (m.getMatrix()[z.getX()][z.getY()-1].equals("7")) 
				|| (m.getMatrix()[z.getX()][z.getY()-1].equals("8")) || (m.getMatrix()[z.getX()][z.getY()-1].equals("2"))) 
				&& !m.getMatrix()[z.getX()][z.getY()-2].equals("1")) {
			m.getMatrix()[z.getX()][z.getY()]="0";
			z.setY(z.getY()-2);
			m.getMatrix()[z.getX()][z.getY()]="3";
		}
		
		//NEGA IL MOVIMENTO
		else
			z.setDir(true);
	}
	
	//CONTROLLA SE PUO' CADERE PER EFFETTO DELLA GRAVITA'
	public void gravity(Matrix m, ZenChan z) {
		//CADE VERSO IL BASSO SE SOTTO DI SE' HA IL VUOTO O IL PLAYER
		if(m.getMatrix()[z.getX()+1][z.getY()].equals("0") || m.getMatrix()[z.getX()+1][z.getY()].equals("5")) {
			m.getMatrix()[z.getX()][z.getY()]="0";
			m.getMatrix()[z.getX()+1][z.getY()]="3";
			z.setX(z.getX()+z.getSpeed());
		}	
		
		//SE CADENDO INCONTRA UN OGGETTO CHE PUO' ESSERE RACCOLTO, LO OLTREPASSA
		else if(((m.getMatrix()[z.getX()+1][z.getY()].equals("6")) ||(m.getMatrix()[z.getX()+1][z.getY()].equals("7")) 
				|| (m.getMatrix()[z.getX()+1][z.getY()].equals("8")) || (m.getMatrix()[z.getX()+1][z.getY()].equals("2"))) 
				&& !m.getMatrix()[z.getX()+2][z.getY()].equals("1")) {
			m.getMatrix()[z.getX()][z.getY()]="0";
			m.getMatrix()[z.getX()+2][z.getY()]="3";
			z.setX(z.getX()+z.getSpeed()+1);
		}
	}
	
	//CONTROLLO EFFETTO PAC-MAN
	public void respawnZenChan(Matrix m, ZenChan z) {
		m.getMatrix()[z.getX()][z.getY()]="0";
		z.setX(1);
		z.setY(m.getColumns()/2);
	}
}
