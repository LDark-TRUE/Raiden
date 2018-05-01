package org.ldark.raiden;

public class EnermyBullet {
	int x,y;
	
	EnermyBullet(int x,int y){
		this.x=x;
		this.y=y;
	}
	public void EBmove(){
		y+=5;
	}
}
