package com.sleepbank.screens;

import java.util.Scanner;

import com.sleepbank.beans.User;
import com.sleepbank.util.AppState;

public class UserHomepage implements Screen {

	public static final UserHomepage uh=new UserHomepage();
	private User u=new User();
	private Scanner scan=new Scanner(System.in);
	
	private UserHomepage() {
		
	}

	public Screen start() {
		
		u=AppState.state.getCurrentUser();
		if(u.getMyAccounts().size()==1 && u.getMyAccounts().get(0).getType().equals("standard")) {
			// only has standard account, give option to open quality boosting account
			
			return StandardAccountHomepage.sah;

		}
		else if(u.getMyAccounts().size()==1) {
			// only has quality boosting account, give option to open standard account
			
			return QualityBoostingHomepage.qbh;
		
		}
		else {
			// has both accounts
			
			return StandardAndQualityHomepage.saqh;
			
		}
		
	}
	
	

}