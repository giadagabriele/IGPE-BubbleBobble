package actionManager;

import gameObjects.Bubble;
import manager.Matrix;

//GESTORE MOVIMENTO BOLLE
public class BubbleMovement {

	public void movement(Matrix m, Bubble b) {
		//CONTROLLA SE E' POSSIBILE MUOVERSI VERSO DESTRA, SI MUOVE SOLO SE NON CI SONO OSTACOLI DAVANTI A SE
		//ALTRIMENTI, ESPLODE
		if(b.isDx()) {
			if(m.getMatrix()[b.getX()][b.getY()+1].equals("0")) 
				b.setY(b.getY()+1);
			 
			else
				b.setVisible(false);
		}
		
		//CONTROLLA SE E' POSSIBILE MUOVERSI VERSO SINISTRA, SI MUOVE SOLO SE NON CI SONO OSTACOLI DAVANTI A SE
		//ALTRIMENTI, ESPLODE
		else {
			if(m.getMatrix()[b.getX()][b.getY()-1].equals("0"))	
				b.setY(b.getY()-1);
			
			else 
				b.setVisible(false);
		}
	}
}