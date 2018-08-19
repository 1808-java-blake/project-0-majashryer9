package com.revature.screens;

import java.util.Scanner;

import com.revature.beans.User;
import com.revature.daos.UserDao;
import com.revature.util.AppState;

public class CheckingAndSavings implements Screen {
	
	public static final CheckingAndSavings cas=new CheckingAndSavings();
	private UserDao ud=UserDao.currentUserDao;
	private Scanner scan=new Scanner(System.in);
	private User u=new User();
	
	private CheckingAndSavings() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public Screen start() {
		u=AppState.state.getCurrentUser();
		System.out.println("Do you want to: ");
		System.out.println("(1) Make a deposit in checking");
		System.out.println("(2) Make a deposit in savings");
		System.out.println("(3) Make a withdrawal from checking");
		System.out.println("(4) Make a withdrawal from savings");
		System.out.println("(5) View balances");
		System.out.println("(6) View transaction history");
		System.out.println("(7) Transfer money to another account");
		System.out.println("(8) Logout");
		String input=scan.nextLine();
		
		
		if(input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5") || input.equals("6") || input.equals("7") || input.equals("8")) {
			
			switch (Integer.parseInt(input)) {
			case 1: // deposit in checking
				
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
			
			case 2: // deposit in savings 
				
				System.out.println("Enter deposit amount: ");
				String toAdd1=scan.nextLine().trim();
				try {
					double toDouble=Double.parseDouble(toAdd1);
					if(toDouble >= 0) {
						u.getSavingsAccount().deposit(toDouble);
						System.out.println("Your balance is now " + u.getSavingsAccount().getAccountBalance());
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
				
			case 3: // withdraw from checking
				
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
				
			case 4: // withdraw from savings 
				
				System.out.println("Enter withdrawal amount: ");
				String toTake1=scan.nextLine().trim();
				try {
					double toDouble=Double.parseDouble(toTake1);
					if(toDouble >= 0) {
						u.getSavingsAccount().withdraw(toDouble);
						System.out.println("Your balance is now " + u.getSavingsAccount().getAccountBalance());
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
			
			case 5:
				
				u.getCheckingAccount().viewBalance();
				u.getSavingsAccount().viewBalance();
				
				break;
			case 6:
				
				u.viewHistory();
				
				break;
				
			case 7:
				System.out.println("Enter the user name of the person you want to transfer money to: ");
				String username=scan.nextLine().trim();
				User to=new User();
				to=ud.adminGetUser(username);
				if(to!=null) {
					System.out.println("Enter amount you want to deposit in " + to.getFirstName() + "'s account: ");
					String toTransfer=scan.nextLine();
					try {
						double toWithdraw=Double.parseDouble(toTransfer);
						System.out.println("Do you want to take the transfer money from your (1) checking or (2) savings? ");
						String s=scan.nextLine();
						if(s.equals("1") || s.equals("2")) {
							double amount=0;
							if(s.equals("1")) {
								amount=u.getCheckingAccount().withdraw(toWithdraw);
							}
							else {
								amount=u.getSavingsAccount().withdraw(toWithdraw);
							}
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
							
						}
						
					} catch(NullPointerException e) {
						
						System.out.println("Can't find that user");
						
					}
					catch (Exception e) {
						// TODO: handle exception
						System.out.println("Invalid input.");
					}
					
				}
				
				break;
				
				
			case 8:
				System.out.println("Logging out.");
				AppState.state.setCurrentUser(null);
				return Homepage.hp;
			}
			
		}
		else {
			System.out.println("Invalid input.");
		}
		
		return CheckingAndSavings.cas;
	}
	
	

}
