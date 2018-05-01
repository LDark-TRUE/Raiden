package org.ldark.raiden;


public class Bullet extends GameObjection {
	
		int mfbx;
		int mfby;
		Bullet(int mfbx, int mfby){
			super(mfbx,mfby);
			this.mfbx=mfbx;
			this.mfby=mfby;
		}
		
		public void move(){
			mfby-=8;
		}
		
}
