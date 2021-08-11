package actionManager;

import gameCharacters.LittleDragon;
import manager.Matrix;

//GESTORE MOVIMENTO DRAGO
public class LittleDragonMovement {
	
	//CONTROLLA SE E' POSSIBILE MUOVERSI VERSO DESTRA
	public void movementR(Matrix m, LittleDragon bub) {
		//SI MUOVE SE DAVANTI A SE HA IL VUOTO
		if(m.getMatrix()[bub.getX()][bub.getY()+1].equals("0")) {
			m.getMatrix()[bub.getX()][bub.getY()]="0";
			bub.move(LittleDragon.MOVE_RIGHT);
			m.getMatrix()[bub.getX()][bub.getY()]="5";
		}
		
		//SE INCONTRA UN OGGETTO CHE PUO' ESSERE RACCOLTO, LO PRENDE E AUMENTA IL PUNTEGGIO
		else if(m.getMatrix()[bub.getX()][bub.getY()+1].equals("6") || m.getMatrix()[bub.getX()][bub.getY()+1].equals("7")) {
			m.getMatrix()[bub.getX()][bub.getY()]="0";
			bub.move(LittleDragon.MOVE_RIGHT);
			bub.setScore(bub.getScore()+100);
			m.getMatrix()[bub.getX()][bub.getY()]="5";
		}
		
		//SE INCONTRA UN OGGETTO DI TIPO VITA, LO PRENDE E AUMENTA LE SUE VITE SE SONO MINORI DI 5
		else if(m.getMatrix()[bub.getX()][bub.getY()+1].equals("8")) {
			m.getMatrix()[bub.getX()][bub.getY()]="0";
			bub.move(LittleDragon.MOVE_RIGHT);
			
			if(bub.getLife()<5)
				bub.setLife(bub.getLife()+1);
			m.getMatrix()[bub.getX()][bub.getY()]="5";
		}
		else if(m.getMatrix()[bub.getX()][bub.getY()+1].equals("1") && bub.getY()==29)
		{
			m.getMatrix()[bub.getX()][bub.getY()]="0";
			//bub.setX(bub.getX());
			bub.setY(1);
		}	
	}
	
	//CONTROLLA SE E' POSSIBILE MUOVERSI VERSO DESTRA
	public void movementL(Matrix m, LittleDragon bub) {
		//SI MUOVE SE DAVANTI A SE HA IL VUOTO
		if (m.getMatrix()[bub.getX()][bub.getY()-1].equals("0")) {
			m.getMatrix()[bub.getX()][bub.getY()]="0";
			bub.move(LittleDragon.MOVE_LEFT);
			m.getMatrix()[bub.getX()][bub.getY()]="5";
		}	
		
		//SE INCONTRA UN OGGETTO CHE PUO' ESSERE RACCOLTO, LO PRENDE E AUMENTA IL PUNTEGGIO
		else if(m.getMatrix()[bub.getX()][bub.getY()-1].equals("6") || m.getMatrix()[bub.getX()][bub.getY()-1].equals("7")) {
			m.getMatrix()[bub.getX()][bub.getY()]="0";
			bub.move(LittleDragon.MOVE_LEFT);
			bub.setScore(bub.getScore()+100);
			m.getMatrix()[bub.getX()][bub.getY()]="5";
		}
		
		//SE INCONTRA UN OGGETTO DI TIPO VITA, LO PRENDE E AUMENTA LE SUE VITE SE SONO MINORI DI 5
		else if(m.getMatrix()[bub.getX()][bub.getY()-1].equals("8")) {
			m.getMatrix()[bub.getX()][bub.getY()]="0";
			bub.move(LittleDragon.MOVE_LEFT);
			if(bub.getLife()<5)
				bub.setLife(bub.getLife()+1);
			m.getMatrix()[bub.getX()][bub.getY()]="5";
		}
	}
	
	//CONTROLLA SE PUO' SALTARE
	public void jump(Matrix m, LittleDragon bub) {
		//SALTA SE SOPRA DI SE' C'E' IL VUOTO
		if(m.getMatrix()[bub.getX()-1][bub.getY()].equals("0") && bub.getX()-1>0) {
			m.getMatrix()[bub.getX()][bub.getY()]="0";
			bub.move(LittleDragon.MOVE_UP);
			m.getMatrix()[bub.getX()][bub.getY()]="5";
		}
		
		//SE INCONTRA UN OGGETTO CHE PUO' ESSERE RACCOLTO, LO PRENDE E AUMENTA IL PUNTEGGIO 
		else if((m.getMatrix()[bub.getX()-1][bub.getY()].equals("6") || m.getMatrix()[bub.getX()-1][bub.getY()].equals("7"))  && bub.getX()-1>0) {
			m.getMatrix()[bub.getX()][bub.getY()]="0";
			bub.move(LittleDragon.MOVE_UP);
			bub.setScore(bub.getScore()+100);
			m.getMatrix()[bub.getX()][bub.getY()]="5";
		}
		
		//SE INCONTRA UN OGGETTO DI TIPO VITA, LO PRENDE E AUMENTA LE SUE VITE SE SONO MINORI DI 5
		else if(m.getMatrix()[bub.getX()-1][bub.getY()].equals("8")  && bub.getX()-1>0) {
			m.getMatrix()[bub.getX()][bub.getY()]="0";
			bub.move(LittleDragon.MOVE_UP);
			if(bub.getLife()<5)
				bub.setLife(bub.getLife()+1);
			m.getMatrix()[bub.getX()][bub.getY()]="5";
		}
		
		//NEGA IL SALTO
		else
			bub.setUp(false);
	}
	
	//CONTROLLA SE PUO' CADERE PER EFFETTO DELLA GRAVITA'
	public void gravity(Matrix m, LittleDragon bub){
		//CADE VERSO IL BASSO SE SOTTO DI SE' HA IL VUOTO
		if(m.getMatrix()[bub.getX()+1][bub.getY()].equals("0")) {
			m.getMatrix()[bub.getX()][bub.getY()]="0";
			m.getMatrix()[bub.getX()+1][bub.getY()]="5";
			bub.setX(bub.getX()+bub.getSpeed());
		}	
		
		//SE CADENDO INCONTRA UN NEMICO, PERDE UNA VITA
		else if(m.getMatrix()[bub.getX()+1][bub.getY()].equals("2") || m.getMatrix()[bub.getX()+1][bub.getY()].equals("3")) {
			m.getMatrix()[bub.getX()][bub.getY()]="0";
			bub.setLife(bub.getLife()-1);
			bub.setX(1);
			bub.setY(1);
		}
		
		//SE CADENDO INCONTRA UN OGGETTO CHE PUO' ESSERE RACCOLTO, LO PRENDE E AUMENTA IL PUNTEGGIO
		else if(m.getMatrix()[bub.getX()+1][bub.getY()].equals("6") || m.getMatrix()[bub.getX()+1][bub.getY()].equals("7")) {
			m.getMatrix()[bub.getX()][bub.getY()]="0";
			bub.setScore(bub.getScore()+100);
			m.getMatrix()[bub.getX()+1][bub.getY()]="5";
			bub.setX(bub.getX()+bub.getSpeed());
		}
		
		//SE CADENDO INCONTRA UN OGGETTO DI TIPO VITA, LO PRENDE E AUMENTA LE SUE VITE SE SONO MINORI DI 5
		else if(m.getMatrix()[bub.getX()+1][bub.getY()].equals("8")) {
			m.getMatrix()[bub.getX()][bub.getY()]="0";
			
			if(bub.getLife()<5)
				bub.setLife(bub.getLife()+1);
			
			m.getMatrix()[bub.getX()+1][bub.getY()]="5";
			bub.setX(bub.getX()+bub.getSpeed());
		}
	}
	
	//CONTROLLO EFFETTO PAC-MAN
	public void respawnBub(Matrix m, LittleDragon bub) {
			m.getMatrix()[bub.getX()][bub.getY()]="0";
			bub.setX(1);
			bub.setY(m.getColumns()/2);
	}

}
