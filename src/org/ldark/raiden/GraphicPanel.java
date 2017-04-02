package org.ldark.raiden;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.sound.midi.Synthesizer;
import javax.swing.ImageIcon;

public class GraphicPanel {

	Image hero;
	Image bullet;
	Image enermy;
	Image blast;
	Image EB;

	//String Path = this.getClass().getResource("res/timg.jpg").getFile();
	/*
	 * Image hero = new ImageIcon("res/image/hero.gif").getImage(); Image bullet
	 * = new ImageIcon("res/image/herozidan.gif").getImage(); Image enermy = new
	 * ImageIcon("res/image/diji.gif").getImage(); Image blast = new
	 * ImageIcon("res/image/bomb_0.gif").getImage(); Image EB = new
	 * ImageIcon("res/image/dijizidan.gif").getImage(); // Image Back=new
	 * ImageIcon("res/image/timg.jpg").getImage(); // String
	 * Path="res/image/timg.jpg";
	 */
	GameCore gc;
	GameCondition gcdt;
	BufferedImage bfim = null;

	GraphicPanel(GameCore gc, GameCondition gcdt) {
		this.gc = gc;
		this.gcdt = gcdt;
		try {
			bfim = loadImage("timg.jpg");
			hero = loadImage("hero.gif");
			bullet = loadImage("herozidan.gif");
			enermy = loadImage("diji.gif");
			blast = loadImage("bomb_0.gif");
			EB = loadImage("dijizidan.gif"); // Image
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//System.out.println(Path);
	}

	private BufferedImage loadImage(String path) throws IOException {
		URL imgUrl = this.getClass().getResource("res/" + path);
		return ImageIO.read(imgUrl);
	}

	public void render(Graphics g) {
		// long a = System.currentTimeMillis();
		synchronized (gc) {
			// System.out.println(Thread.currentThread().getId());
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, gc.getPanelW(), gc.getPanelH());
			/*
			 * g.drawImage(Back, 0, 0, 500, gc.backy, null);¾É°æ±³¾°Í¼»æÖÆ
			 * g.drawImage(Back, 0, gc.backy, 500,640-gc.backy,null);
			 */
			// BufferedImage ubfim=null;
			Image ubfim = null;
			Image ubfim2 = null;
			ubfim = bfim.getSubimage(0, 0, 500, 640 - gc.backy);
			ubfim2 = bfim.getSubimage(0, 640 - gc.backy, 500, gc.backy);
			g.drawImage(ubfim, 0, gc.backy, null);
			g.drawImage(ubfim2, 0, 0, null);
			g.drawString((int) gc.score + "·Ö", 10, 40);

			g.drawImage(hero, gc.getMf().x, gc.getMf().y, null);
			for (Bullet b : gc.HerobulletList) {// ×Óµ¯µÄ»æÖÆ
				g.drawImage(bullet, b.mfbx, b.mfby, null);
			}
			for (enermy e : gc.Enermy) {
				g.drawImage(enermy, e.x, e.y, null);
			}
			for (Blast b : gc.blast) {
				g.drawImage(blast, b.Bx, b.By, null);
				b.flag++;
			}
			for (EnermyBullet EBullet : gc.EB) {
				g.drawImage(EB, EBullet.x, EBullet.y, null);
			}
		}
		// System.out.println(System.currentTimeMillis() - a + "ms");
	}

}
