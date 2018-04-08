package August;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{
	Image im=null;
	public void paint(Graphics g){
		im=new ImageIcon("1.png").getImage();
		g.drawImage(im, 0, 0,MAIN.m.getWidth(),185,null);
	}
}
