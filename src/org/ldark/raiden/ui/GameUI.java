package org.ldark.raiden.ui;

import org.ldark.raiden.GameCore;
import org.ldark.raiden.GraphicPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameUI {
	private JPanel contentPane;

	private GameCore Gc;
	private long startTime;
	private long frameCount;
	private int start = 0;

	public GameUI(JFrame frame) {
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent keyEvent) {
				switch (keyEvent.getKeyCode()) {
					case KeyEvent.VK_UP:
						Gc.setUp(true);
						break;
					case KeyEvent.VK_DOWN:
						Gc.setDown(true);
						break;
					case KeyEvent.VK_LEFT:
						Gc.setLeft(true);
						break;
					case KeyEvent.VK_RIGHT:
						Gc.setRight(true);
						break;
					case KeyEvent.VK_Z:
						Gc.setZ(true);
						break;
					case KeyEvent.VK_SPACE:
						Gc.setSpace(true);
						break;
					case KeyEvent.VK_ENTER:
						if (start == 0) {
							new Thread(() -> {//单独的线程来进行游戏逻辑的处理
								startTime = System.currentTimeMillis();
								while (true) {
									Gc.run();
									contentPane.repaint();
									try {
										Thread.sleep(16);
									} catch (InterruptedException e1) {
										// TODO 自动生成的 catch 块
										e1.printStackTrace();
									}
								}
							}).start();
						}
						start = 1;
						break;
					case KeyEvent.VK_0:
						Gc.StopMusic();
				}
			}

			@Override
			public void keyReleased(KeyEvent keyEvent) {
				switch (keyEvent.getKeyCode()) {
					case KeyEvent.VK_UP:
						Gc.setUp(false);
						break;
					case KeyEvent.VK_DOWN:
						Gc.setDown(false);
						break;
					case KeyEvent.VK_LEFT:
						Gc.setLeft(false);
						break;
					case KeyEvent.VK_RIGHT:
						Gc.setRight(false);
						break;
					case KeyEvent.VK_Z:
						Gc.setZ(false);
						break;
					case KeyEvent.VK_SPACE:
						Gc.setSpace(false);
						break;
				}
			}
		});
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("GameUI");
		frame.setContentPane(new GameUI(frame).contentPane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(50, 50, 500, 640);
		frame.setVisible(true);
	}

	private void createUIComponents() {
		// TODO: place custom component creation code here
		Gc = new GameCore(500, 640);
		GraphicPanel Gp = new GraphicPanel(Gc, Gc.gcdt);
		contentPane = new RealPane(Gp);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
	}

	// RealPane类
	class RealPane extends JPanel {
		Image ImageBuffer = null;
		Graphics GraImage = null;

		private GraphicPanel Gp;

		RealPane(GraphicPanel Gp) {
			this.Gp = Gp;
		}

		@Override
		public void paint(Graphics g) {
			Gp.render(g);//负责绘图
			g.setColor(Color.WHITE);
			g.drawString((double) frameCount / ((double) (System.currentTimeMillis() - startTime) / 1000) + " fps", 10,
					20);
			frameCount++;
			if (start == 0) g.drawString("Enter", 200, 300);
			// FPS计算
		}

		@Override
		public void update(Graphics g) {//缓冲
			if (GraImage == null) {
				ImageBuffer = createImage(getWidth(), getHeight());
				GraImage = ImageBuffer.getGraphics();
			}
			paint(GraImage);
			g.drawImage(ImageBuffer, getX(), getY(), null);
		}

	}
}
