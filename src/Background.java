import javax.swing.*;
import java.awt.*;
 class Background extends JPanel{

	 ImageIcon icon = new ImageIcon("image/tic.png");
	 java.awt.Image img = icon.getImage();
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		
		g.drawImage(img, 280,10,450,500, this);//(750-400,0,750,550)
}
 }
 