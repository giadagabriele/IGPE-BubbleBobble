package manager;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import actionManager.BubbleMovement;
import actionManager.GhostMovement;
import actionManager.LittleDragonMovement;
import actionManager.ZenChanMovement;
import audio.Music;
import collisions.Bub_Ghost;
import collisions.Bub_ZenChan;
import collisions.Bubble_ZenChan;
import gameCharacters.Ghost;
import gameCharacters.LittleDragon;
import gameCharacters.ZenChan;
import gameObjects.Apple;
import gameObjects.Banana;
import gameObjects.Block;
import gameObjects.Bubble;
import gameObjects.Heart;

public class GameManager implements KeyListener {

	LittleDragon bub;
	Ghost ghost;
	LittleDragonMovement playerMovement;
	ZenChanMovement enemyMovement;
	BubbleMovement bubbleMovement;
	GhostMovement ghostMovement;
	Bubble_ZenChan enemyDeath;
	Bub_ZenChan playerDeathZ;
	Bub_Ghost playerDeathG;
	public Vector<ZenChan> enemies;
	ArrayList<Bubble> bubbles;
	Block block;
	Apple apple;
	Banana banana;
	Heart heart;
	public Matrix m;
	public Image img;
	public JLabel endImg;
	Dimension dim;
	Music music;
	boolean isEnemyDead=false;
	boolean isEnemyDeadGravity=false;
	boolean active=true;
	boolean end=false;
	boolean next=true;
	
	public GameManager() {
		m=new Matrix(Matrix.level);
		block=new Block(0,0,true);
		apple=new Apple(0,0,true);
		banana=new Banana(0,0,true);
		heart=new Heart(0,0,true);
		bubbles=new ArrayList<Bubble>();
		playerMovement=new LittleDragonMovement();
		enemyMovement=new ZenChanMovement();
		bubbleMovement=new BubbleMovement();
		ghostMovement=new GhostMovement();
		enemyDeath=new Bubble_ZenChan();
		playerDeathZ=new Bub_ZenChan();
		playerDeathG=new Bub_Ghost();
		enemies=new Vector<ZenChan>();
		music=new Music();
		
		for(int i=0; i<m.getRows(); i++) {
			for(int j=0; j<m.getColumns(); j++) {
				if(m.getMatrix()[i][j].equals("5"))
					bub=new LittleDragon(i,j,1,5,0);
				
				if(m.getMatrix()[i][j].equals("3"))
				{
					ZenChan zenChan =new ZenChan(i,j,1);
					enemies.add(zenChan);
				}
			}
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {		
	}
		
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getExtendedKeyCode()) {
			case KeyEvent.VK_LEFT:
				playerMovement.movementL(m,bub);
				break;
			
			case KeyEvent.VK_RIGHT:
				playerMovement.movementR(m,bub);
				break;
			
			case KeyEvent.VK_UP:
				if(bub.getCont()==0 && m.getMatrix()[bub.getX()+1][bub.getY()].equals("1"))
					bub.setUp(true);
				break;
				
			case KeyEvent.VK_SPACE:
				Bubble bubble=new Bubble(bub.getX(),bub.getY(),1,true, bub.getDirection()==0);
				if(bub.getDirection()==0) {	//DESTRA
					if(m.getMatrix()[bub.getX()][bub.getY()+1].equals("0"))
					{
						bubble.setX(bub.getX());
						bubble.setY(bub.getY());
						bubble.setVisible(true);
						bubble.setDx(true);
					}
				}
				
				if(bub.getDirection()==1) {	//SINISTRA
					if (m.getMatrix()[bub.getX()][bub.getY()-1].equals("0"))
					{
						bubble.setX(bub.getX());
						bubble.setY(bub.getY());
						bubble.setVisible(true);
						bubble.setDx(false);
					}
				}
				
				bubbles.add(bubble);
				break;
				
			case KeyEvent.VK_O:
				if(music.isMusicState() && Music.clip!=null) {
					music.stopMusic();
				}
				
				else if(!music.isMusicState() && Music.clip!=null) {
					music.startMusic();
				}
				break;
			
			default:
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {		
	}

	//GESTIONE GIOCO, CONTROLLA IL MOVIMENTO DEL PLAYER, DELLE BOLLE E DEI NEMICI, LE COLLISIONI E LO STATO DI VITTORIA O SCONFITTA
	public void play() throws IOException {			
		
		//MOVIMENTO PLAYER
		
		//****CONTROLLO SUL SALTO DEL PLAYER****
		if(bub.isUp()==false || bub.getCont()==4) {
			playerMovement.gravity(m, bub);
			
			if(bub.getX()==m.getRows()-1)
				playerMovement.respawnBub(m, bub);
			
			if(bub.getCont()>0)
				bub.setCont(bub.getCont()-1);
			
			bub.setUp(false);
		}
		
		else if(bub.isUp() && bub.getCont()<4) {
			playerMovement.jump(m,bub);	
			
			if(m.getMatrix()[bub.getX()][bub.getY()].equals("2") || m.getMatrix()[bub.getX()][bub.getY()].equals("3"))
			{
				bub.setLife(bub.getLife()-1);
				bub.setX(1);
				bub.setY(1);
			}
			
			bub.setCont(bub.getCont()+1);
		}
		//FINE CONTROLLO SUL SALTO
		
		//SE IL PLAYER SALTANDO COLLIDE CON UN NEMICO E LE SUE VITE ARRIVANO A ZERO, ALLORA IL GIOCO TERMINA
		if(bub.getLife()==0)
			end=true;
		
		//SE IL PLAYER NON E' MORTO, ALLORA AVVIENE IL CONTROLLO SUL RESTO DEL GIOCO
		else {
			//MUOVIMENTO BOLLE
			for(int j=0; j<bubbles.size(); j++)
				bubbleMovement.movement(m, bubbles.get(j));
				
			//MOVIMENTO NEMICI
			for(int i=0; i<enemies.size(); i++) {
				if(enemies.get(i).isDir()) {	//VERIFICA SE IL NEMICO STA ANDANDO A DESTRA
					enemyMovement.movementR(m, enemies.get(i));
					
					for(int j=0; j<bubbles.size(); j++) {
						//SE IL NEMICO VIENE COLPITO DA UNA BOLLA, MUORE E AL SUO POSTO COMPARE UN OGGETTO SCELTO CASUALMENTE TRA QUELLI DISPONIBILI
						if(enemyDeath.collision(m, bubbles.get(j))) {						
							int n=3;
							Random random=new Random();						
							int k=random.nextInt(n);
							
							if(k==0) 
								m.getMatrix()[enemies.get(i).getX()][enemies.get(i).getY()]="6";
							
							
							else if(k==1) 
								m.getMatrix()[enemies.get(i).getX()][enemies.get(i).getY()]="7";
							
							else 
								m.getMatrix()[enemies.get(i).getX()][enemies.get(i).getY()]="8";
							
							
							enemies.remove(i);
							isEnemyDead=true;
							break;
						}
					}
				}
			
				else if(enemies.get(i).isDir()==false) {	//VERIFICA SE IL NEMICO STA ANDANDO A SINISTRA
					enemyMovement.movementL(m, enemies.get(i));
					
					for(int j=0; j<bubbles.size(); j++)
						//SE IL NEMICO VIENE COLPITO DA UNA BOLLA, MUORE E AL SUO POSTO COMPARE UN OGGETTO SCELTO CASUALMENTE TRA QUELLI DISPONIBILI
						if(enemyDeath.collision(m, bubbles.get(j))) {						
							int n=3;
							Random random=new Random();
							int k=random.nextInt(n);
							
							if(k==0) 
								m.getMatrix()[enemies.get(i).getX()][enemies.get(i).getY()]="6";
							
		
							else if(k==1) 
								m.getMatrix()[enemies.get(i).getX()][enemies.get(i).getY()]="7";
							
							
							else 
								m.getMatrix()[enemies.get(i).getX()][enemies.get(i).getY()]="8";
							
							enemies.remove(i);
							isEnemyDead=true;
							break;
						}
					}
				
				//SE IL NEMICO NON E' MORTO, EFFETTUA I CONTROLLI SULLA COLLISIONE CON IL PLAYER 
				if(!isEnemyDead) {
					if(playerDeathZ.collision(m, bub, enemies.get(i))) { 	
						bub.setLife(bub.getLife()-1);
						bub.setX(1);
						bub.setY(1);
					}
					
					enemyMovement.gravity(m, enemies.get(i));
					
					for(int j=0; j<bubbles.size(); j++) {	//CONTROLLA SE IL NEMICO VIENE COLPITO DURANTE LA CADUTA 
						if(enemyDeath.collision(m, bubbles.get(j))) {						
							int n=3;
							Random random=new Random();
							int k=random.nextInt(n);
							
							if(k==0) 
								m.getMatrix()[enemies.get(i).getX()][enemies.get(i).getY()]="6";
							
		
							else if(k==1) 
								m.getMatrix()[enemies.get(i).getX()][enemies.get(i).getY()]="7";
							
						
							else 
								m.getMatrix()[enemies.get(i).getX()][enemies.get(i).getY()]="8";
							
							enemies.remove(i);
							isEnemyDeadGravity=true;
							break;
						}
					}
					
					//SE IL NEMICO NON E' MORTO, CONTROLLA SIA L'EVENTUALE COLLISIONE CON IL PLAYER SIA L'EFFETTO PAC-MAN IN CASO DI CADUTA NEL VUOTO
					if(!isEnemyDeadGravity) {
						if(enemies.get(i).getX()==m.getRows()-1)
							enemyMovement.respawnZenChan(m, enemies.get(i));
						
						if(playerDeathZ.collision(m, bub, enemies.get(i))) {
							bub.setLife(bub.getLife()-1);
							bub.setX(1);
							bub.setY(1);
						}
					}
				}
			}	
			
			//SOLO SE SI E' AL TERZO LIVELLO, ALLORA VIENE CONTROLLATO IL MOVIMENTO DEL GHOST
			if(Matrix.level==3)
			{	
				//MOVIMENTO GHOST
				ArrayList<Integer> directions=new ArrayList<Integer>();
				
				if(ghost.isDirRow()) 
					directions.add(Ghost.MOVE_RIGHT);
				
				if(!ghost.isDirRow()) 
					directions.add(Ghost.MOVE_LEFT);
				
				if(ghost.isDirColumn()) 
					directions.add(Ghost.MOVE_UP);
				
				if(!ghost.isDirColumn()) 
					directions.add(Ghost.MOVE_DOWN);
				
				if(!directions.isEmpty()) {
					int n=directions.size();
					Random random=new Random();						
					int k=random.nextInt(n);
					
					if(directions.get(k)==Ghost.MOVE_RIGHT)
						ghostMovement.movementR(m, ghost);
					
					if(directions.get(k)==Ghost.MOVE_LEFT) 
						ghostMovement.movementL(m, ghost);
					
					if(directions.get(k)==Ghost.MOVE_UP) 
						ghostMovement.movementU(m, ghost);
						
					if(directions.get(k)==Ghost.MOVE_DOWN) 
						ghostMovement.movementD(m, ghost);
				}	
				
				//CONTROLLO COLLISIONE CON IL PLAYER
				if(playerDeathG.collision(m, bub, ghost)) {
					bub.setLife(bub.getLife()-1);
					bub.setX(1);
					bub.setY(1);
				}
				
				//CONTROLLO EFFETTO PAC-MAN
				if(ghost.getX()==0)
					ghostMovement.respawnGhostDown(m, ghost);
				
				if(ghost.getX()==m.getRows()-1)
					ghostMovement.respawnGhostUp(m, ghost);
				//FINE CONTROLLO
			}
			
			//SE LE VITE DEL PLAYER SONO PARI A ZERO, ALLORA IL GIOCO TERMINA
			if(bub.getLife()==0) 
				end=true;
			
			//CONTROLLO PASSAGGIO DI LIVELLO
			if(enemies.size()==0) {
				for(int i=0; i<m.getRows(); i++)
					for(int j=0; j<m.getColumns(); j++)
						if(m.getMatrix()[i][j].equals("6") || m.getMatrix()[i][j].equals("7") || m.getMatrix()[i][j].equals("8"))
							next=false;
						
				if(next) {
					Matrix.level++;
					
					if(Matrix.level==4) 
						end=true;
					
					if(!end)
						nextLevel(Matrix.level);
				}	
			}
			
			next=true;
			isEnemyDead=false;
			isEnemyDeadGravity=false;
		}
	}
	//FINE DEL METODO PLAY()
	
	//QUESTO METODO VERIFICA SE, UNA VOLTA TERMINATO IL GIOCO, SI VUOLE RICOMINCIARE O MENO. FUNZIONA SIA PER LA VITTORIA CHE PER LA SCONFITTA
	public boolean endGame() throws IOException {	
		JFrame f=new JFrame();
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		JPanel panel=new JPanel();
		if(bub.getLife()==0)
			img=ImageIO.read(new File("images/gameOver.jpg"));
		
		else
			img=ImageIO.read(new File("images/victory.jpg"));

		endImg = new JLabel(new ImageIcon(img.getScaledInstance((int)dim.getWidth(), (int)dim.getHeight(), Image.SCALE_SMOOTH)));
		f.setSize(dim);
		f.add(panel);
		panel.add(endImg);
		f.setResizable(true);
		f.setVisible(true);
		Object[] possibilities= {"Yes","No"};
		int options=JOptionPane.showOptionDialog(f, "PLAY AGAIN?"," ",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null, possibilities, possibilities[0]);
		
		if(options==0) {
			Matrix.level=1;
			f.setVisible(false);
			return true;
		}
		
		else
			return false;
	}
	
	//METODO CHE GENERA I LIVELLI SUCCESSIVI AL PRIMO
	public void nextLevel(int level) {
		m=new Matrix(level);
		bubbles.clear();
		
		for(int i=0; i<m.getRows(); i++) {
			for(int j=0; j<m.getColumns(); j++) {
				if(m.getMatrix()[i][j].equals("5"))
				{
					bub.setX(i);
					bub.setY(j);
				}
				
				if(Matrix.level==3)
					if(m.getMatrix()[i][j].equals("2"))
						ghost=new Ghost(i,j,1,true);
				
				if(m.getMatrix()[i][j].equals("3"))
				{
					ZenChan zenChan =new ZenChan(i,j,1);
					enemies.add(zenChan);
				}
			}
		}
	}
	
	//METODO CHE PERMETTE DI FAR RIPARTIRE IL GIOCO DALL'INIZIO
	public void restarting() {
		Matrix.level=1;
		m=new Matrix(Matrix.level);
		enemies.clear();
		
		for(int i=0; i<m.getRows(); i++) {
			for(int j=0; j<m.getColumns(); j++) {
				if(m.getMatrix()[i][j].equals("5")) 
					bub=new LittleDragon(i,j,1,5,0);
		
				if(m.getMatrix()[i][j].equals("3"))
				{
					ZenChan zenChan =new ZenChan(i,j,1);
					enemies.add(zenChan);
				}
			}
		}
	}
}