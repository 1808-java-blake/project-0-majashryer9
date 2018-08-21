package com.sleepbank.screens;

import java.util.Scanner;

import com.sleepbank.beans.SleepAccount;
import com.sleepbank.beans.User;
import com.sleepbank.daos.UserDao;
import com.sleepbank.util.AppState;

public class StandardAndQualityHomepage implements Screen {
	
	public static final StandardAndQualityHomepage saqh=new StandardAndQualityHomepage();
	private Scanner scan=new Scanner(System.in);
	private User u=new User();
	
	@Override
	public Screen start() {
		
		u=AppState.state.getCurrentUser();
		System.out.println("Select an option:");
		System.out.println("(1) Make a deposit");
		System.out.println("(2) Make a withdrawal");
		System.out.println("(3) View account balances");
		System.out.println("(4) View transaction history");
		System.out.println("(5) Transfer sleep power to another user");
		System.out.println("(6) Logout");
		String choice=scan.nextLine().trim();
		if(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4") || 
			choice.equals("5") || choice.equals("6")) {
			
			int key=Integer.parseInt(choice);
			switch (key) {
			case 1:
				
				return DepositScreen.ds;
				
			case 2:
				
				return WithdrawScreen.ws;
				
			case 3:
				
				for(SleepAccount sa : u.getMyAccounts()) {
					sa.viewAccountBalance();
				}
				
				break;
			case 4:
				for(SleepAccount sa : u.getMyAccounts()) {
					sa.viewTransactionHistory();
				}
				break;
			case 5:
				
				return TransferMoneyFromEitherScreen.tmfe;
				
			case 6:
				System.out.println("Logging out.");
				AppState.state.setCurrentUser(null);
				return Homescreen.hs;
				
			}	
			
		}
		else {
			System.out.println("Invalid input.");
		}
		
		return saqh;
	}

}
