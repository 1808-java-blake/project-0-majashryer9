package com.revature.screens;

import java.time.Instant;
import java.util.Scanner;

import com.revature.beans.CheckingAccount;
import com.revature.beans.SavingsAccount;
import com.revature.beans.User;
import com.revature.daos.UserDao;

public class UserHomepage implements Screen {
	
	private User u;
	Scanner scan=new Scanner(System.in);
	public static final UserDao ud=UserDao.currentUserDao;

	public UserHomepage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserHomepage(User u) {
		super();
		this.u = u;
	}

	public Screen start() {
		if(u.getSavingsAccount()==null) { // only has a checking account
			
			return new CheckingNoSavingsHomepage(u);
			
		}
		else if(u.getCheckingAccount()==null) { //only has a savings account
			return new SavingsNoCheckingHomepage(u);
		}
		else { // has both
			
			return new CheckingAndSavings(u);
			
		}
		
	}
		

}

