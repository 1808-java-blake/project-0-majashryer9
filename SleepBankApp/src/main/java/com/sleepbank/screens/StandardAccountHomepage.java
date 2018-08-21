package com.sleepbank.screens;

import java.util.Scanner;

import com.sleepbank.beans.SleepAccount;
import com.sleepbank.beans.User;
import com.sleepbank.daos.AccountDao;
import com.sleepbank.daos.UserDao;
import com.sleepbank.util.AppState;

public class StandardAccountHomepage implements Screen {
	
	public static final StandardAccountHomepage sah=new StandardAccountHomepage();
	private Scanner scan=new Scanner(System.in);
	private User u=new User();
	
	
	@Override
	public Screen start() {
		
		u=AppState.state.getCurrentUser();
		System.out.println("Select an option:");
		System.out.println("(1) Make a deposit");
		System.out.println("(2) Make a withdrawal");
		System.out.println("(3) View account balance");
		System.out.println("(4) View transaction history");
		System.out.println("(5) Open a Quality Boosting Account");
		System.out.println("(6) Transfer sleep power to another user");
		System.out.println("(7) Logout");
		String choice=scan.nextLine().trim();
		if(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4") || 
			choice.equals("5") || choice.equals("6") || choice.equals("7") || choice.equals("8")) {
			
			int key=Integer.parseInt(choice);
			switch (key) {
			case 1:
				
				return DepositStandardScreen.dss;
				
			case 2:
				
				return WithdrawStandardScreen.wss;
				
			case 3:
				
				u.getMyAccounts().get(0).viewAccountBalance();
				break;
				
			case 4:
				u.getMyAccounts().get(0).viewTransactionHistory();
				break;
			case 5:
				
				System.out.println("You have selected to open a Quality Boosting Account. Type 'quality' to proceed.");
				if(scan.nextLine().trim().toLowerCase().equals("quality")) {
					// create quality boosting account
					SleepAccount newAccountQB=new SleepAccount();
					newAccountQB.setTotalSleepPower(0);
					newAccountQB.setType("quality boosting");
					u.getMyAccounts().add(newAccountQB);
					AccountDao.currentAccountDao.createAccount(u.getUsername(), newAccountQB);
					return StandardAndQualityHomepage.saqh;
					
				}
				else {
					return StandardAccountHomepage.sah;
				}
				
			case 6:
				
				return TransferMoneyFromStandardHomepage.tmfs;
				
			case 7:
				System.out.println("Logging out.");
				AppState.state.setCurrentUser(null);
				return Homescreen.hs;
				
			}
			
			
		}
		else {
			System.out.println("Invalid input.");
		}
		
		
		
		return sah;
	}
	
	
	
	

}
