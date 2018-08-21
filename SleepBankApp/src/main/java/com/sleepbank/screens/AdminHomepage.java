package com.sleepbank.screens;

import java.util.Scanner;

import com.sleepbank.beans.SleepAccount;
import com.sleepbank.beans.User;
import com.sleepbank.daos.UserDao;

public class AdminHomepage implements Screen {
	
	public static final AdminHomepage ah=new AdminHomepage();
	private Scanner scan=new Scanner(System.in);
	
	@Override
	public Screen start() {
		// TODO Auto-generated method stub
		System.out.println("Enter a username to view an account or press (1) to go back: ");
		String username=scan.nextLine().trim();
		if(username.equals("1")) {
			return Homescreen.hs;
		}
		User u=new User();
		u=UserDao.currentUserDao.adminGetUser(username);
		if(u==null) {
			System.out.println("Cannot find user with that username.");
		}
		else {
			while(true) {
				System.out.println("Select an option: ");
				System.out.println("(1) To view user's account balance(s)");
				System.out.println("(2) To view user's transaction history");
				System.out.println("(3) To exit");
				String selection=scan.nextLine().trim();
				if(selection.equals("1")) {
					for(SleepAccount sa : u.getMyAccounts()) {
						sa.viewAccountBalance();
					}
				}
				else if(selection.equals("2")) {
					
					for(SleepAccount sa: u.getMyAccounts()) {
						sa.viewTransactionHistory();
					}
					
				}
				else if(selection.equals("3")) {
					break;
				}
				else {
					System.out.println("Invalid input.");
				}
			}
			
		}
	
		return ah;
	}

}