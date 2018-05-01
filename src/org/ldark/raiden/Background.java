package org.ldark.raiden;

public class Background {
	int y = 1;

	public int moveBack() {
		y += 1;
		if (y >= 640)
			y = 1;
		return y;
	}
}
