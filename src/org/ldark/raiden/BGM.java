package org.ldark.raiden;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class BGM {
	AudioClip ac;
	URL path = this.getClass().getResource("res/OldMemory.wav");
	//URL st2=this.getClass().getResource(str);


	public void play() {

		ac = Applet.newAudioClip(path);
		ac.loop();
		//ac=Applet.newAudioClip(st2);
		//ac.loop();
	}

	public void stop() {
		ac.stop();
	}

}
//打开音乐的方法
