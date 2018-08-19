package com.revature.screens;

import java.time.Instant;
import java.util.Scanner;

import com.revature.beans.CheckingAccount;
import com.revature.beans.SavingsAccount;
import com.revature.beans.User;
import com.revature.daos.UserDao;
import com.revature.util.AppState;

public class UserHomepage implements Screen {
	
	public static final UserHomepage usehome=new UserHomepage();
	Scanner scan=new Scanner(System.in);
	public static final UserDao ud=UserDao.currentUserDao;
	private User u=new User();

	private UserHomepage() {
		
	}

	public Screen start() {
		u=AppState.state.getCurrentUser();
		if(u.getSavingsAccount()==null) { // only has a checking account
			
			return CheckingNoSavingsHomepage.cnsh;
			
		}
		else if(u.getCheckingAccount()==null) { //only has a savings account
			
			u.getSavingsAccount().accrueInterest();
			return SavingsNoCheckingHomepage.snch;
			
		}
		else { // has both
			
			u.getSavingsAccount().accrueInterest();
			return CheckingAndSavings.cas;
			
		}
		
	}
		

}

