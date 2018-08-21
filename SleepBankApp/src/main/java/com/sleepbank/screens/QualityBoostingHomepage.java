package com.sleepbank.screens;

import java.util.Scanner;

import com.sleepbank.beans.SleepAccount;
import com.sleepbank.beans.User;
import com.sleepbank.daos.AccountDao;
import com.sleepbank.daos.UserDao;
import com.sleepbank.util.AppState;

public class QualityBoostingHomepage implements Screen {
	
	public static final QualityBoostingHomepage qbh=new QualityBoostingHomepage();
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
		System.out.println("(5) Open a Standard Account");
		System.out.println("(6) Transfer sleep power to another user");
		System.out.println("(7) Logout");
		String choice=scan.nextLine().trim();
		if(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4") || 
			choice.equals("5") || choice.equals("6") || choice.equals("7")) {
			
			int key=Integer.parseInt(choice);
			switch (key) {
			case 1:
				
				return DepositQualityScreen.dqs;
				
			case 2:
				
				return WithdrawQualityScreen.wqs;
				
			case 3:
				
				u.getMyAccounts().get(0).viewAccountBalance();
				break;
				
			case 4:
				u.getMyAccounts().get(0).viewTransactionHistory();
				break;
			case 5:
				System.out.println("You have selected to open a Standard Account. Type 'standard' to proceed.");
				if(scan.nextLine().trim().toLowerCase().equals("standard")) {
					// create standard account
					SleepAccount newAccountS=new SleepAccount();
					newAccountS.setTotalSleepPower(0);
					newAccountS.setType("standard");
					u.getMyAccounts().add(newAccountS);
					AccountDao.currentAccountDao.createAccount(u.getUsername(), newAccountS);
					return StandardAndQualityHomepage.saqh;
					
				}
				else {
					return QualityBoostingHomepage.qbh;
				}
			case 6:
				
				return TransferMoneyFromQualityHomepage.tmfq;
				
			case 7:
				
				System.out.println("Logging out.");
				AppState.state.setCurrentUser(null);
				return Homescreen.hs;
				
			}
			
			
		}
		else {
			System.out.println("Invalid input.");
		}

		return qbh;
	}
	
	

}
