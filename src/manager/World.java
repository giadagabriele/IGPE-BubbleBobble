package manager;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import javax.swing.JPanel;
import gameObjects.Bubble;
import manager.GameManager;

public class World extends JPanel implements Runnable {
	
	int height_img;
	int width_img;
	GameManager game;
	
	public World(int w, int h) throws IOException {
		this.setBackground(Color.BLACK);
		game=new GameManager();
		height_img=h/game.m.getRows();
		width_img=w/game.m.getColumns();
	}
	
	@Override 
	protected void paintComponent(Graphics g) {		//SI OCCUPA DI DISEGNARE LA MAPPA CON I RELATIVI PERSONAGGI ED OGGETTI
		super.paintComponent(g);		
		
		g.setColor(Color.WHITE);
		g.drawString("LIFES: "+game.bub.getLife()+"     SCORE: "+game.bub.getScore() ,25,25);
		g.drawString("LEVEL: "+Matrix.level,25,50);
		
		if(game.music.isMusicState())
			g.drawString("MUSIC ON",100,50);
		
		else
			g.drawString("MUSIC OFF",100,50);
		
		for(int i=0; i<game.bubbles.size(); i++) {
			if(game.bubbles.get(i).isVisible()) {
				Bubble bubble = game.bubbles.get(i);
				g.drawImage(bubble.getImg(), bubble.getY()*width_img, bubble.getX()*height_img, width_img, height_img, null);
			}
			
			else {
				game.bubbles.remove(i);
			}
		}		
		
		for(int i=0; i<game.m.getRows(); i++)
			for(int j=0; j<game.m.getColumns(); j++) {
				if(game.m.getMatrix()[i][j].equals("1"))
					g.drawImage(game.block.getImg(), j*width_img, i*height_img, width_img, height_img, null);
					
				if(game.m.getMatrix()[i][j].equals("5"))
					g.drawImage(game.bub.getImg(), j*width_img, i*height_img, width_img, height_img, null);
				
				if(game.m.getMatrix()[i][j].equals("6"))
					g.drawImage(game.apple.getImg(), j*width_img, i*height_img, width_img, height_img, null);
				
				if(game.m.getMatrix()[i][j].equals("7"))
					g.drawImage(game.banana.getImg(), j*width_img, i*height_img, width_img, height_img, null);
				
				if(game.m.getMatrix()[i][j].equals("8"))
					g.drawImage(game.heart.getImg(), j*width_img, i*height_img, width_img, height_img, null);
				
				if(game.m.getMatrix()[i][j].equals("2") && game.ghost != null) 
					g.drawImage(game.ghost.getImg(), j*width_img, i*height_img, width_img, height_img, null);
		}
		
		for(int i = 0; i < game.enemies.size(); i++) {
			g.drawImage(game.enemies.get(i).getImg(), game.enemies.get(i).getY()*width_img, game.enemies.get(i).getX()*height_img, width_img, height_img, null);			
		}
	}
	
	@Override
	public void run() {		//UN THREAD PRESENTE NELLA CLASSE START FARA' PARTIRE QUESTA RUN, CHE GESTISCE L'INTERO GIOCO
		this.addKeyListener(game);
		while(true) {
			repaint();	
			try {
				if(game.end==false) {
					Start.frame.setVisible(true);
					game.play();
				}
				
				else {
					Start.frame.setVisible(false);
					if(game.endGame()) {
						game.end=false;
						game.restarting();
					}
					
					else
						System.exit(0);
				}
				Thread.sleep(120);
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
			}
		}
	}	
}
