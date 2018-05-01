package org.ldark.raiden;

public class enermy {
	int x,y;
	int time;
	
	public enermy(){
		x=(int)(Math.random()*450);
		y=0;
	}
	
	public void moveEnermy(){
		y+=3;
	}
	
	/*public void EMAttribute(){
		x=(int)(Math.random()*450);
		y=0;
	}*/
}
