package com.revature.screens;

import java.time.Instant;
import java.util.Scanner;

import com.revature.beans.Account;
import com.revature.beans.CheckingAccount;
import com.revature.beans.SavingsAccount;
import com.revature.beans.User;
import com.revature.daos.UserDao;

public class Registration implements Screen {
	
	private Scanner scan=new Scanner(System.in);
	public UserDao ud=UserDao.currentUserDao;
	private String username;
	private String password;

	public Registration() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Registration(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}



	public Screen start() {
		User u=new User();
		u=ud.getUser(username, password);
		if(u==null) {
			return new Registration(username, password);
		}
		System.out.println("Welcome " + u.getFirstName() + " to Sublime Bank Corp!");
		System.out.println("Select account to open: ");
		System.out.println("(1) Checking Account");
		System.out.println("(2) Savings Account");
		System.out.println("(3) Checkings and Savings Account");
		String input=scan.nextLine();
		if(input.equals("1") || input.equals("2") || input.equals("3")) {
			switch (Integer.parseInt(input)) {
			case 1:  // opening a checkingAccount
				
				CheckingAccount checking=new CheckingAccount();
				System.out.println("Enter amount to deposit in your new checking account. You must deposit at least $1.");
				String checkingAmount=scan.nextLine();
				if(checkingAmount.length() > 0 && (48 <= checkingAmount.charAt(0) && checkingAmount.charAt(0) <= 57)) {
					double toDeposit=Double.parseDouble(checkingAmount);
					checking.setUser(u);
					checking.setAccountBalance(toDeposit);
					u.setCheckingAccount(checking);
					ud.updateUser(u);
					return new UserHomepage(u);
				}
				else {
					System.out.println("Invalid input.");
				}
				// return this screen
				return new Registration(username, password);

			case 2: // open savings account registration screen
				
				SavingsAccount savings=new SavingsAccount();
				System.out.println("Enter amount to deposit in your new savings account. You must deposit at least $1.");
				String savingsAmount=scan.nextLine();
				if(savingsAmount.length() > 0 && (48 <= savingsAmount.charAt(0) && savingsAmount.charAt(0) <= 57)) {
					double toDeposit=Double.parseDouble(savingsAmount);
					savings.setUser(u);
					savings.setAccountBalance(toDeposit);
					savings.setInstantNow(Instant.now());
					u.setSavingsAccount(savings);
					ud.updateUser(u);
					return new UserHomepage(u);
				}
				else {
					System.out.println("Invalid input.");
				}		
				// return this screen
				return new Registration(username, password);
				
			case 3: // open account registration screen
				
				CheckingAccount checkingBoth=new CheckingAccount();
				System.out.println("Enter amount to deposit in your new checking account. You must deposit at least $1.");
				String checkingAmountBoth=scan.nextLine();
				if(checkingAmountBoth.length() > 0 && (48 <= checkingAmountBoth.charAt(0) && checkingAmountBoth.charAt(0) <= 57)) {
					double toDeposit=Double.parseDouble(checkingAmountBoth);
					checkingBoth.setUser(u);
					checkingBoth.setAccountBalance(toDeposit);
					u.setCheckingAccount(checkingBoth);
					SavingsAccount savingsBoth=new SavingsAccount();
					System.out.println("Enter amount to deposit in your new savings account. You must deposit at least $1.");
					String savingsAmountBoth=scan.nextLine();
					if(savingsAmountBoth.length() > 0 && (48 <= savingsAmountBoth.charAt(0) && savingsAmountBoth.charAt(0) <= 57)) {
						double toDepositSavings=Double.parseDouble(savingsAmountBoth);
						savingsBoth.setUser(u);
						savingsBoth.setAccountBalance(toDepositSavings);
						u.setSavingsAccount(savingsBoth);
					}
					ud.updateUser(u);
					return new UserHomepage(u);
				}
				
				return new Registration(username, password);
			}
		}
		else {
			System.out.println("Invalid Input.");
		}
		
		return new Registration(username, password);
	}

}
