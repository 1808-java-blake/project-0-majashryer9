package com.sleepbank.screens;

import java.util.Scanner;

import com.sleepbank.beans.SleepAccount;
import com.sleepbank.beans.User;
import com.sleepbank.daos.AccountDao;
import com.sleepbank.util.AppState;

public class AccountRegistration implements Screen {
	
	public static final AccountRegistration ar=new AccountRegistration();
	private Scanner scan=new Scanner(System.in);
	private User u=new User();
	
	private AccountRegistration() {
		
	}

	public Screen start() {
		
		u=AppState.state.getCurrentUser();
		System.out.println("Welcome to NO SLEEP DEBT-PRIVATION BANK CORP");
		System.out.println("Do you want to open a");
		System.out.println("(1) Standard Sleep Account");
		System.out.println("(2) Quality Boosting Sleep Account");
		System.out.println("(3) Both (Open both now to get two free hours of 'good night's sleep' quality sleep power!)");
		String input=scan.nextLine();
		if(input.equals("1") || input.equals("2") || input.equals("3"))
		{	int key=Integer.parseInt(input);
			switch (key) {
			case 1:
				SleepAccount newAccountS=new SleepAccount();
				newAccountS.setTotalSleepPower(0);
				newAccountS.setType("standard");
				u.getMyAccounts().add(newAccountS);
				AccountDao.currentAccountDao.createAccount(u.getUsername(), newAccountS);
				return UserHomepage.uh;
		
			case 2:
				SleepAccount newAccountQB=new SleepAccount();
				newAccountQB.setTotalSleepPower(0);
				newAccountQB.setType("quality boosting");
				u.getMyAccounts().add(newAccountQB);
				AccountDao.currentAccountDao.createAccount(u.getUsername(), newAccountQB);
				return UserHomepage.uh;
				
			case 3:
				SleepAccount newAccount1=new SleepAccount();
				SleepAccount newAccount2=new SleepAccount();
				newAccount1.setTotalSleepPower(4*2);
				newAccount2.setTotalSleepPower(4*2);
				newAccount1.setType("standard");
				newAccount2.setType("quality boosting");
				u.getMyAccounts().add(newAccount1);
				u.getMyAccounts().add(newAccount2);
				AccountDao.currentAccountDao.createAccount(u.getUsername(), newAccount1);
				AccountDao.currentAccountDao.createAccount(u.getUsername(), newAccount2);
				return UserHomepage.uh;
			
			}	
		}
		else {
			System.out.println("Invalid input.");
		}
		
		return ar;
	}
	

}
