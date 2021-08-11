package manager;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JFrame;
import audio.Music;

//CLASSE CHE PERMETTE L'AVVIO DEL GIOCO TRAMITE IL THREAD T, CHE AVVIA LA run() PRESENTE NELLLA CLASSE WORLD
public class Start {
	public static JFrame frame=new JFrame("Bubble Bobble");
	private Thread t;
	
	public void Game() throws IOException {
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			World w=new World((int)dim.getWidth(), (int)dim.getHeight());
			Music music=new Music();
			music.playSound("audio/"+"musicTheme" + ".mid");
			frame.setSize(dim);
			frame.add(w);
			w.setFocusable(true);
			t=new Thread(w);
			t.start();
			frame.setResizable(false);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	
}