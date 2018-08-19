package com.revature.screens;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Scanner;

import com.revature.beans.Account;
import com.revature.beans.CheckingAccount;
import com.revature.beans.SavingsAccount;
import com.revature.beans.User;
import com.revature.daos.UserDao;
import com.revature.util.AppState;

public class Registration implements Screen {
	
	public static final Registration r=new Registration();
	private Scanner scan=new Scanner(System.in);
	public UserDao ud=UserDao.currentUserDao;
	private User u=new User();

	private Registration() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Screen start() {
		u=AppState.state.getCurrentUser();
		System.out.println(u.getFirstName());
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
				try {
					double toDeposit=Double.parseDouble(checkingAmount);
					checking.setUser(u);
					checking.setAccountBalance(toDeposit);
					u.setCheckingAccount(checking);
					ud.updateUser(u);
					return UserHomepage.usehome;
					
				} catch (Exception e) {
					System.out.println("Invalid input.");
					// TODO: handle exception
				}
				// return this screen
				return Registration.r;

			case 2: // open savings account registration screen
				
				SavingsAccount savings=new SavingsAccount();
				System.out.println("Enter amount to deposit in your new savings account. You must deposit at least $1.");
				String savingsAmount=scan.nextLine();
				try {
					double toDeposit=Double.parseDouble(savingsAmount);
					savings.setUser(u);
					savings.setAccountBalance(toDeposit);
					savings.setLastLogin(Instant.now());
					savings.setInterestRate(.3);
					u.setSavingsAccount(savings);
					ud.updateUser(u);
					return UserHomepage.usehome;	
					
				} catch (Exception e) {
					System.out.println("Invalid input.");
					// TODO: handle exception
				}
				// return this screen
				return Registration.r;
				
			case 3: // open account registration screen
				
				CheckingAccount checkingBoth=new CheckingAccount();
				System.out.println("Enter amount to deposit in your new checking account. You must deposit at least $1.");
				String checkingAmountBoth=scan.nextLine();
				try {
					double toDeposit=Double.parseDouble(checkingAmountBoth);
					checkingBoth.setUser(u);
					checkingBoth.setAccountBalance(toDeposit);
					u.setCheckingAccount(checkingBoth);
					SavingsAccount savingsBoth=new SavingsAccount();
					System.out.println("Enter amount to deposit in your new savings account. You must deposit at least $1.");
					String savingsAmountBoth=scan.nextLine();
					try {
						double toDepositSavings=Double.parseDouble(savingsAmountBoth);
						savingsBoth.setUser(u);
						savingsBoth.setAccountBalance(toDepositSavings);
						savingsBoth.setLastLogin(Instant.now());
						savingsBoth.setInterestRate(.5);
						u.setSavingsAccount(savingsBoth);
						
					} catch (Exception e) {
						System.out.println("Invalid input.");
						// TODO: handle exception
					}
					ud.updateUser(u);
					return UserHomepage.usehome;
					
				} catch (Exception e) {
					System.out.println("Invalid input");
					// TODO: handle exception
				}
				
				return Registration.r;
			}
		}
		else {
			System.out.println("Invalid Input.");
		}
		
		return Registration.r;
	}

}
