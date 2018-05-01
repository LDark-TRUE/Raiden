package org.ldark.raiden;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UI extends JFrame {

	private static final long serialVersionUID = -2574708289101570555L;

	private JPanel contentPane;

	private long startTime;

	private long frameCount;

	private int start = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI frame = new UI();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UI() {
		super("Raiden 1.1.0 beta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 500, 640);

		GameCore Gc = new GameCore(getWidth(), getHeight());
		GraphicPanel Gp = new GraphicPanel(Gc, Gc.gcdt);

		contentPane = new RealPane(Gp);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		addKeyListener(new KeyAdapter() {// 按下按钮
			@Override
			public void keyPressed(KeyEvent e) {
				//	System.out.println("asd");
				switch (e.getKeyCode()) {
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
		});
		addKeyListener(new KeyAdapter() {// 释放按钮
			@Override
			public void keyReleased(KeyEvent e) {
				//System.out.println("asd2");
				switch (e.getKeyCode()) {
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

		/*panel.setBounds(0, 0, 450, 501);
		panel.setLayout(null);
		JButton btnNewButton = new JButton("\u5F00\u59CB\u6E38\u620F");
		btnNewButton.setBounds(120, 94, 200, 55);
		btnNewButton.setFocusable(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
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
		});
		panel.add(btnNewButton);

		contentPane.add(panel);
*/
	}

	// RealPane类
	class RealPane extends JPanel {

		/**
		 *
		 */
		private static final long serialVersionUID = -3741019044684089379L;
		/**
		 *
		 */
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

		Image ImageBuffer = null;
		Graphics GraImage = null;

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
