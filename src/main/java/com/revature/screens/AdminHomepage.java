package com.revature.screens;

import java.util.Scanner;

import com.revature.beans.User;

public class AdminHomepage implements Screen {
	
	private User u;
	private Scanner scan=new Scanner(System.in);

	public AdminHomepage() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public AdminHomepage(User u) {
		super();
		this.u = u;
	}



	@Override
	public Screen start() {
		// TODO Auto-generated method stub
		System.out.println("Select an option: ");
		System.out.println("(1) View user account(s) and balance(s)");
		System.out.println("(2) View user transaction history");
		System.out.println("(3) to Logout");
		String input=scan.nextLine();
		if(input.equals("1") || input.equals("2") || input.equals("3")) {
			
			if(input.equals("1")) {
				if(u.getSavingsAccount()==null) { // only has checking
					u.getCheckingAccount().viewBalance();
				}
				else if(u.getCheckingAccount()==null) { // only has savings
					u.getSavingsAccount().viewBalance();
				}
				else { // has both
					u.getCheckingAccount().viewBalance();
					u.getSavingsAccount().viewBalance();
				}
				
			}
			else if(input.equals("2")){
				System.out.println(u.getFirstName() + " " + u.getLastName() + "'s transaction history: ");
				u.viewHistory();
			}
			else {
				System.out.println("Logging out.");
				return Homepage.hp;
			}
			
		}
		else {
			System.out.println("Invalid input.");
		}
		
		
		return new AdminHomepage(u);
	}

}
