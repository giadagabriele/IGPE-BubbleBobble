package manager;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//CLASSE IN CUI VIENE GENERATO IL MENU INIZIALE
public class Menu { 

	public static void main(String[] args) throws IOException {
		JFrame frame=new JFrame("Bubble Bobble");
		JPanel menu=new JPanel();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		Image img=ImageIO.read(new File("images/start.jpg"));
		JLabel logo=new JLabel(new ImageIcon(img.getScaledInstance((int)dim.getWidth(), (int)dim.getHeight(),Image.SCALE_SMOOTH)));
		frame.add(menu);
		menu.add(logo);
		Start begin=new Start();

		frame.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					try {
						begin.Game();
						frame.setVisible(false);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				
			}
		});
			
		frame.setSize(dim);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}