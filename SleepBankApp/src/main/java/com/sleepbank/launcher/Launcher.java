package com.sleepbank.launcher;

import com.sleepbank.screens.Homescreen;
import com.sleepbank.screens.Screen;

public class Launcher {
	
	public static void main(String[] args) {
		Screen s=Homescreen.hs;
		while(true) {
			if(s!=null) {
				s=s.start();
			}
			else {
				s=Homescreen.hs;
			}
		}
	}

}
