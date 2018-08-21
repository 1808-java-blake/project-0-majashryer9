package com.sleepbank.screens;

import java.util.Scanner;

import com.sleepbank.beans.User;
import com.sleepbank.daos.UserDao;
import com.sleepbank.util.AppState;

public class Login implements Screen {
	
	public static final Login l=new Login();
	private Scanner scan=new Scanner(System.in);
	private User u=new User();

	private Login() {
		
	}

	public Screen start() {
		
		System.out.println("Enter username: ");
		String username=scan.nextLine().trim();
		System.out.println("Enter password: ");
		String password=scan.nextLine().trim();
		u=UserDao.currentUserDao.findByUsernameAndPassword(username, password);
		AppState.state.setCurrentUser(u);
		if(u==null) {
			System.out.println("Invalid credentials.");
			return l;
		}
		return UserHomepage.uh;
	}
	
	
	
	
	
	

}
