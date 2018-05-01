package org.ldark.raiden;

import java.util.ArrayList;
import java.util.List;

public class GameCore {
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	int flag;
	int time = 0;
	int music = 0;

	int tx, ty;
	int hbsize;
	int removeblast;
	int em2;
	int timeforspace = 0;
	int backy = 1;
	static int startx = 200, starty = 540;
	int score = 0;
	static int death = 0;
	protected List<Bullet> HerobulletList = new ArrayList<Bullet>();// 英雄飞机子弹
	protected List<enermy> Enermy = new ArrayList<enermy>();// 敌机
	protected List<Blast> blast = new ArrayList<Blast>();// 爆炸效果
	protected List<EnermyBullet> EB = new ArrayList<EnermyBullet>();

	GameCondition gcdt = new GameCondition();
	Impact imp = new Impact();
	BGM bgm = new BGM();

	public MyFighter getMf() {
		return mf;
	}

	private boolean z;
	private boolean space;
	int PanelW;
	int PanelH;

	GameCore(int PanelW, int PanelH) {// 构造函数
		this.PanelW = PanelW;
		this.PanelH = PanelH;
		tx = mf.x;
		ty = mf.y;
		PlayMusic();

	}

	public void PlayMusic() {
		bgm.play();
	}

	public void StopMusic() {
		bgm.stop();
	}

	public int getPanelW() {
		return PanelW;
	}

	public int getPanelH() {
		return PanelH;
	}

	MyFighter mf = new MyFighter(time);
	Background back = new Background();
	// Bullet bt=new Bullet();

	public void setUp(boolean up) {
		this.up = up;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public void setZ(boolean z) {
		this.z = z;
	}

	public void setSpace(boolean space) {
		this.space = space;
	}

	/**
	 * @author LDark
	 */
	public synchronized void run() {
		backy = back.moveBack();
		if (up) {
			mf.y -= 5;
			if (mf.y < -10)
				mf.y = -10;
			ty = mf.y;
		}
		if (down) {
			mf.y += 5;
			if (mf.y > PanelH - 40)
				mf.y = PanelH - 40;
			ty = mf.y;
		}
		if (left) {
			mf.x -= 5;
			if (mf.x < -10)
				mf.x = -10;
			tx = mf.x;
			// System.out.println(mf.x);
		}
		if (right) {
			mf.x += 5;
			if (mf.x > PanelW - 30)
				mf.x = PanelW - 30;
			tx = mf.x;
		}
		// 子弹的位置计算 bulletstart
		if (z && time % 5 == 0) {
			// gcdt.setFlag(1);
			Bullet b = new Bullet(mf.x, mf.y);
			HerobulletList.add(b);
			if (time == 6000000)
				time = 0;
			timeforspace = 0;
			// gcdt.setBulletNumber(i);
			// System.out.println(i);
		}

		time += 1;
		for (Bullet bu : HerobulletList) {
			bu.move();
		}

		hbsize = HerobulletList.size();
		for (int i = 0; i < hbsize; i++) {
			Bullet hb = HerobulletList.get(i);
			if (hb.mfby < 0) {
				HerobulletList.remove(i);
				hbsize--;
				i--;
			}

		}

		if (z == false) {
			gcdt.setFlag(0);
		} // 子弹的位置计算bulletend
		// System.out.println(""+mf.x);

		if (space == true) {
			if (time - timeforspace > 300) {// 5秒一个大
				HerobulletList.removeAll(HerobulletList);
				Enermy.removeAll(Enermy);
				blast.removeAll(blast);
				EB.removeAll(EB);
				/*
				 * HerobulletList = new ArrayList<Bullet>();// 英雄飞机子弹 Enermy =
				 * new ArrayList<enermy>();// 敌机 blast = new
				 * ArrayList<Blast>();//爆炸效果 EB = new ArrayList<EnermyBullet>();
				 */
				timeforspace = time;
			}
		}

		int enm = 0;// 敌机位置start
		enm = Enermy.size();

		if (time % 30 == 0 && enm < 7) {
			enermy newenermy = new enermy();
			newenermy.time = time;
			// EnermyBullet newEB= new EnermyBullet(newenermy.x,newenermy.y);
			Enermy.add(newenermy);
			// EB.add(newEB);
		}

		for (enermy e : Enermy) {
			e.moveEnermy();
		}

		for (int i = 0; i < enm; i++) {
			enermy e = Enermy.get(i);
			if (e.y > PanelH) {
				Enermy.remove(i);
				enm--;
				i--;
			}
		} // 敌机位置end

		// 敌机子弹start
		for (enermy e : Enermy) {
			if (time - e.time > 20) {
				e.time = time;
				EnermyBullet newEB = new EnermyBullet(e.x + 15, e.y + 20);
				EB.add(newEB);
			}
		}

		for (EnermyBullet eb : EB) {
			eb.EBmove();
		}
		int ebnum;
		ebnum = EB.size();

		for (int i = 0; i < ebnum; i++) {
			EnermyBullet eb = EB.get(i);
			if (eb.y > PanelH) {
				EB.remove(i);
				ebnum--;
				i--;
			}
		} // 敌机子弹end

		// 英雄子弹start
		for (int i = 0; i < hbsize; i++) {
			int x1 = HerobulletList.get(i).mfbx;
			int y1 = HerobulletList.get(i).mfby;
			for (int j = 0; j < enm; j++) {
				int x2 = Enermy.get(j).x;
				int y2 = Enermy.get(j).y;
				if (imp.Rect(x1, y1, x1, y1 + 50, x2 + 22, y2 + 22) < 10
						|| imp.Rect(x1, y1, x1 + 64, y1, x2 + 22, y2 + 22) < 10
						|| imp.Rect(x1, y1 + 50, x1 + 64, y1 + 50, x2 + 22, y2 + 22) < 10
						|| imp.Rect(x1 + 64, y1, x1 + 64, y1 + 50, x2 + 22, y2 + 22) < 10) {
					score += 10;
					Blast b = new Blast(x1, y1, 1);
					blast.add(b);
					HerobulletList.remove(HerobulletList.get(i));
					Enermy.remove(Enermy.get(j));
					j--;
					i--;
					hbsize--;
					enm--;
					if (enm <= 0)
						break;
					if (hbsize <= 0)
						break;// 此补丁包括：对于子弹的不正确移除（remove）（对同一子弹多次移除）进行修复
					if (i < 0)
						i = 0;
					x1 = HerobulletList.get(i).mfbx;
					y1 = HerobulletList.get(i).mfby;
				}
			}
		}
		removeblast = blast.size();
		for (int i = 0; i < removeblast; i++) {
			Blast b = blast.get(i);
			if (b.flag == 12) {
				blast.remove(i);
				removeblast--;
				i--;
			}
		}
		// 英雄子弹end

		// 飞机碰撞判定
		em2 = Enermy.size();
		for (int i = 0; i < em2; i++) {
			int em2x = Enermy.get(i).x;
			int em2y = Enermy.get(i).y;
			if (time - mf.time > 120) {
				if (imp.Rect(mf.x, mf.y, mf.x + 45, mf.y, em2x + 5, em2y + 5) < 15
						|| imp.Rect(mf.x, mf.y, mf.x + 45, mf.y, em2x + 5, em2y + 5) < 15
						|| imp.Rect(mf.x + 45, mf.y, mf.x + 45, mf.y + 50, em2x + 5, em2y + 5) < 15
						|| imp.Rect(mf.x, mf.y + 50, mf.x + 45, mf.y + 50, em2x + 5, em2y + 5) < 15
						&& time - mf.time > 180) {
					death++;
					score = 0;
					mf.time = time;
					Blast b2 = new Blast(mf.x, mf.y, 1);
					blast.add(b2);

					mf.x = startx;
					mf.y = starty;
					Enermy.remove(i);
					i--;
					em2--;
					if (em2 <= 0)
						break;
					if (i >= 0) {
						em2x = Enermy.get(i).x;// 此补丁包括：解决i=-1的情况。
						em2y = Enermy.get(i).y;
					}
				}
			}

		}
		int removeblast2 = 0;
		removeblast2 = blast.size();
		for (int i = 0; i < removeblast2; i++) {
			Blast b2 = blast.get(i);
			if (b2.flag == 12) {
				blast.remove(i);
				removeblast2--;
				i--;
			}
		}
		/*
		 * for(enermy e:Enermy){
		 * if(e.x-mf.x<20&&mf.x-e.x<20&&e.y-mf.y<20&&mf.y-e.y<20){ mf.x=startx;
		 * mf.y=starty; } }
		 */
		// 飞机碰撞判定end

		// 敌机子弹碰撞判定
		for (EnermyBullet eb : EB) {
			if (time - mf.time > 120) {

				if (imp.Rect(mf.x, mf.y, mf.x + 45, mf.y, eb.x + 5, eb.y + 5) < 6
						|| imp.Rect(mf.x, mf.y, mf.x + 45, mf.y, eb.x + 5, eb.y + 5) < 6
						|| imp.Rect(mf.x + 45, mf.y, mf.x + 45, mf.y + 50, eb.x + 5, eb.y + 5) < 6
						|| imp.Rect(mf.x, mf.y + 50, mf.x + 45, mf.y + 50, eb.x + 5, eb.y + 5) < 6) {
					death++;
					mf.time = time;
					Blast b3 = new Blast(mf.x, mf.y, 1);
					blast.add(b3);
					score = 0;
					mf.x = startx;
					mf.y = starty;
				}
			}
		}
		int removeblast3 = 0;
		removeblast3 = blast.size();
		for (int i = 0; i < removeblast3; i++) {
			Blast b3 = blast.get(i);
			if (b3.flag == 12) {
				blast.remove(i);
				removeblast3--;
				i--;
			}
		}
	}
}
