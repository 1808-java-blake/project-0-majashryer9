package com.revature.launcher;

import com.revature.screens.Homepage;
import com.revature.screens.Screen;

public class Launcher {
	
	public static void main(String[] args) {
		Screen s=Homepage.hp;
		while(true) {
			if(s!=null) {
				s=s.start();
			}
			else {
				s=Homepage.hp;
			}
		}
	}

}
