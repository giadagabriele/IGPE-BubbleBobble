package actionManager;

import gameCharacters.Ghost;
import manager.Matrix;

//GESTORE MOVIMENTO NEMICO SPECIALE (GHOST)
public class GhostMovement {
	
	//CONTROLLA SE E' POSSIBILE MUOVERSI VERSO DESTRA
	public void movementR(Matrix m, Ghost g) {
		//SI MUOVE SE DAVANTI A SE HA IL VUOTO OPPURE IL PLAYER
		if(m.getMatrix()[g.getX()][g.getY()+1].equals("0") || m.getMatrix()[g.getX()][g.getY()+1].equals("5")) {
			m.getMatrix()[g.getX()][g.getY()]="0";
			g.moveG();
			m.getMatrix()[g.getX()][g.getY()]="2";
		}
		
		//SE INCONTRA UN OGGETTO CHE PUO' ESSERE RACCOLTO, LO OLTREPASSA
		else if(((m.getMatrix()[g.getX()][g.getY()+1].equals("6")) || (m.getMatrix()[g.getX()][g.getY()+1].equals("7")) 
				|| (m.getMatrix()[g.getX()][g.getY()+1].equals("8"))) && !m.getMatrix()[g.getX()][g.getY()+2].equals("1")) {
			m.getMatrix()[g.getX()][g.getY()]="0";
			g.setY(g.getY()+2);
			m.getMatrix()[g.getX()][g.getY()]="2";
		}
		
		//NEGA IL MOVIMENTO
		else
			g.setDirRow(false);
	}
	
	//CONTROLLA SE E' POSSIBILE MUOVERSI VERSO SINISTRA
	public void movementL(Matrix m, Ghost g) {
		//SI MUOVE SE DAVANTI A SE HA IL VUOTO OPPURE IL PLAYER
		if(m.getMatrix()[g.getX()][g.getY()-1].equals("0") || m.getMatrix()[g.getX()][g.getY()-1].equals("5")) {
			m.getMatrix()[g.getX()][g.getY()]="0";
			g.moveG();
			m.getMatrix()[g.getX()][g.getY()]="2";
		}	
		
		//SE INCONTRA UN OGGETTO CHE PUO' ESSERE RACCOLTO, LO OLTREPASSA
		else if(((m.getMatrix()[g.getX()][g.getY()-1].equals("6")) || (m.getMatrix()[g.getX()][g.getY()-1].equals("7")) 
				|| (m.getMatrix()[g.getX()][g.getY()-1].equals("8"))) && !m.getMatrix()[g.getX()][g.getY()-2].equals("1"))  {
			m.getMatrix()[g.getX()][g.getY()]="0";
			g.setY(g.getY()-2);
			m.getMatrix()[g.getX()][g.getY()]="2";
		}
		
		//NEGA IL MOVIMENTO
		else
			g.setDirRow(true);
	}
	
	//CONTROLLA SE E' POSSIBILE MUOVERSI VERSO L'ALTO
	public void movementU(Matrix m, Ghost g) {
		//SI MUOVE SE SOPRA DI SE HA IL VUOTO OPPURE IL PLAYER
		if (m.getMatrix()[g.getX()-1][g.getY()].equals("0") || m.getMatrix()[g.getX()-1][g.getY()].equals("5")) {
			m.getMatrix()[g.getX()][g.getY()]="0";
			g.setX(g.getX()-1);
			m.getMatrix()[g.getX()][g.getY()]="2";
		}
		
		//SE INCONTRA UN OGGETTO CHE PUO' ESSERE RACCOLTO, LO OLTREPASSA
		else if(((m.getMatrix()[g.getX()-1][g.getY()].equals("6")) || (m.getMatrix()[g.getX()-1][g.getY()].equals("7")) 
				|| (m.getMatrix()[g.getX()-1][g.getY()].equals("8"))) && !m.getMatrix()[g.getX()-2][g.getY()].equals("1")) {
			m.getMatrix()[g.getX()][g.getY()]="0";
			g.setX(g.getX()-2);
			m.getMatrix()[g.getX()][g.getY()]="2";
		}
		
		//NEGA IL MOVIMENTO
		else
			g.setDirColumn(false);
	}
	
	//CONTROLLA SE E' POSSIBILE MUOVERSI VERSO IL BASSO
	public void movementD(Matrix m, Ghost g) {
		//SI MUOVE SE SOTTO DI SE HA IL VUOTO OPPURE IL PLAYER
		if (m.getMatrix()[g.getX()+1][g.getY()].equals("0") || m.getMatrix()[g.getX()+1][g.getY()].equals("5")) {
			m.getMatrix()[g.getX()][g.getY()]="0";
			g.setX(g.getX()+1);
			m.getMatrix()[g.getX()][g.getY()]="2";
		}
		
		//SE INCONTRA UN OGGETTO CHE PUO' ESSERE RACCOLTO, LO OLTREPASSA
		else if(((m.getMatrix()[g.getX()+1][g.getY()].equals("6")) || (m.getMatrix()[g.getX()+1][g.getY()].equals("7")) 
				|| (m.getMatrix()[g.getX()+1][g.getY()].equals("8"))) && !m.getMatrix()[g.getX()+2][g.getY()].equals("1")) {
			m.getMatrix()[g.getX()][g.getY()]="0";
			g.setX(g.getX()+2);
			m.getMatrix()[g.getX()][g.getY()]="2";
		}
		
		//NEGA IL MOVIMENTO
		else
			g.setDirColumn(true);
	}
	
	//CONTROLLO EFFETTO PAC-MAN, DAL BASSO RICOMPARE IN ALTO
	public void respawnGhostUp(Matrix m, Ghost g) {
		m.getMatrix()[g.getX()][g.getY()]="0";
		g.setX(1);
		g.setY(m.getColumns()/2);
	}
	
	//CONTROLLO EFFETTO PAC-MAN, DALL'ALTO RICOMPARE IN BASSO
	public void respawnGhostDown(Matrix m, Ghost g) {
		m.getMatrix()[g.getX()][g.getY()]="0";
		g.setX(14);
		g.setY(m.getColumns()/2);
	}
}
