package com.revature.screens;

import java.util.Scanner;

import com.revature.beans.User;
import com.revature.util.AppState;

public class AdminHomepage implements Screen {
	
	public static final AdminHomepage ah=new AdminHomepage();
	private User u=new User();
	private Scanner scan=new Scanner(System.in);

	private AdminHomepage() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Screen start() {
		// TODO Auto-generated method stub
		u=AppState.state.getCurrentUser();
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
				AppState.state.setCurrentUser(null);
				u=null;
				return Homepage.hp;
			}
			
		}
		else {
			System.out.println("Invalid input.");
		}
		
		
		return AdminHomepage.ah;
	}

}
