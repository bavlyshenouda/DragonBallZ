package dragonball.view.panels;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public  class MasterPanel extends JPanel {
	

	private static final long serialVersionUID = 1L;

	public static ImageIcon resize(String s,int width, int height) {
			
			BufferedImage image = null;
			
		     try {
			   image = ImageIO.read(new File (s) );
			} catch (IOException e) {
				e.printStackTrace();
			}

		    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
		    Graphics2D g2d = (Graphics2D) bi.createGraphics();
		    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
		    g2d.drawImage(image, 0, 0, width, height, null);
		    g2d.dispose();
		    return new ImageIcon(bi);
		}
  
}
