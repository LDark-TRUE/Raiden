package org.ldark.raiden;



public class GameCondition {//这个类用于做各个类之间的数据交换(还不知道好不好用)测试ing
	int flag=0;
	int BulletNumber=0;
	MyFighter mf;
	int[] bulletposition=new int[256];
	int i=1;
	GameCondition(){
		
	}
	public int getBulletNumber() {
		return BulletNumber;
	}
	public void setBulletNumber(int bulletNumber) {
		this.BulletNumber = bulletNumber;
	}
	
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getFlag() {
		return flag;
	}
	
	public void BP(){
	/*	bulletposition[0]=mf.bullet;
		for( ;i<65536;i++){
			bulletposition[i]=bulletposition[i-1]-15;
			if(bulletposition[i]<0){
				bulletposition[i+1]=mf.bullet;
				BulletNumber--;
				break;
			}
			if(i>65530)i=1;
		}*/
	}
	
}
