package com.revature.screens;

import java.time.Instant;
import java.util.Scanner;

import com.revature.beans.SavingsAccount;
import com.revature.beans.User;
import com.revature.daos.UserDao;
import com.revature.util.AppState;

public class CheckingNoSavingsHomepage implements Screen {
	
	public static final CheckingNoSavingsHomepage cnsh=new CheckingNoSavingsHomepage();
	private UserDao ud=UserDao.currentUserDao;
	private Scanner scan=new Scanner(System.in);
	private User u=new User();

	private CheckingNoSavingsHomepage() {
		
	}
	
	@Override
	public Screen start() {
		
		u=AppState.state.getCurrentUser();
		System.out.println("Do you want to:");
		System.out.println("(1) Make a deposit");
		System.out.println("(2) Make a withdrawal");
		System.out.println("(3) View account balance");
		System.out.println("(4) View transaction history");
		System.out.println("(5) Open a savings account");
		System.out.println("(6) Transfer money to another account");
		System.out.println("(7) Logout");
		
		String input=scan.nextLine();
		if(input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5") || input.equals("6") || input.equals("7")) {
			switch (Integer.parseInt(input)) {
			case 1: // make a deposit
				System.out.println("Enter deposit amount: ");
				String toAdd=scan.nextLine().trim();
				try {
					
					double toDouble=Double.parseDouble(toAdd);
					if(toDouble >= 0) {
						u.getCheckingAccount().deposit(toDouble);
						System.out.println("Your balance is now " + u.getCheckingAccount().getAccountBalance());
						ud.updateUser(u);
					}
					else {
						System.out.println("Enter a positive number.");
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Invalid input.");
				}
				break;
			
			case 2: // make a withdrawal
				System.out.println("Enter withdrawal amount: ");
				String toTake=scan.nextLine().trim();
				try {
					double toDouble=Double.parseDouble(toTake);
					if(toDouble >= 0) {
						u.getCheckingAccount().withdraw(toDouble);
						System.out.println("Your balance is now " + u.getCheckingAccount().getAccountBalance());
						ud.updateUser(u);
					}
					else {
						System.out.println("Enter a positive number.");
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Invalid input.");
				}
				
				break;
				
			case 3: // view account balance
				u.getCheckingAccount().viewBalance();
				break;
			
			case 4: // view transaction history
				u.getCheckingAccount().viewAccountHistory();
				break;
			
			case 5:
				SavingsAccount savings=new SavingsAccount();
				System.out.println("Enter amount to deposit in your new savings account. You must deposit at least $1.");
				String savingsAmount=scan.nextLine();
				try {
					double toDeposit=Double.parseDouble(savingsAmount);
					if(toDeposit >= 0) {
						savings.setUser(u);
						savings.setAccountBalance(toDeposit);
						savings.setLastLogin(Instant.now());
						u.setSavingsAccount(savings);
						ud.updateUser(u);
						return UserHomepage.usehome;
					}
					else {
						System.out.println("Enter a positive number.");
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Invalid input.");
				}
			case 6: // transfer money to another user's account
				System.out.println("Enter the user name of the person you want to transfer money to: ");
				String username=scan.nextLine().trim();
				User to=new User();
				to=ud.adminGetUser(username);
				if(to!=null) {
					System.out.println("Enter amount you want to deposit in " + to.getFirstName() + "'s account: ");
					String toTransfer=scan.nextLine();
					try {
						double toWithdraw=Double.parseDouble(toTransfer);
						double amount=u.getCheckingAccount().withdraw(toWithdraw);
						if(amount > 0) {
							if(to.getSavingsAccount()==null) { // only has checking
								
								to.getCheckingAccount().addFrom(u, amount);
								System.out.println("You have transfered " + amount + " to " + to.getFirstName() + "'s account.");
							}
							else if(to.getCheckingAccount()==null) { // only has savings
								
								to.getSavingsAccount().addFrom(u, amount);
								System.out.println("You have transfered " + amount + " to " + to.getFirstName() + "'s account.");
								
							}
							else {
								System.out.println("Do you want to enter the money into " + to.getFirstName() + "'s (1) checking account"
										+ " or (2) savings accounts?");
								String option=scan.nextLine();
								if(option.equals("1") || option.equals("2")) {
									
									int i=Integer.parseInt(option);
									if(i==1) {
										to.getCheckingAccount().addFrom(u, amount);
										System.out.println("You have transfered " + amount + " to " + to.getFirstName() + "'s account.");
									}
									else {
										to.getSavingsAccount().addFrom(u, amount);
										System.out.println("You have transfered " + amount + " to " + to.getFirstName() + "'s account.");
									}
									
								}
								else {
									System.out.println("Invalid input.");
								}
							}
						}
						ud.updateUser(to);
						
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("Invalid input.");
					}
					
				}
				break;
				
			case 7:
				System.out.println("Logging out.");
				AppState.state.setCurrentUser(null);
				return Homepage.hp;
				
			}
			
		}
		else {
			System.out.println("Invalid input.");
		}
		
		return CheckingNoSavingsHomepage.cnsh;
	}

}
