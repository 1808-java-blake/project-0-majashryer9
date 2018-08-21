package com.sleepbank.screens;

import java.util.Scanner;

import com.sleepbank.beans.User;
import com.sleepbank.daos.UserDao;
import com.sleepbank.util.AppState;

public class UserRegistration implements Screen {
	
	public static final UserRegistration ur=new UserRegistration();
	private Scanner scan=new Scanner(System.in);

	public Screen start() {
		
		User u=new User();
		System.out.println("Enter your first name: ");
		u.setFirstname(scan.nextLine().trim());
		System.out.println("Enter your last name: ");
		u.setLastname(scan.nextLine().trim());
		System.out.println("Enter your username: ");
		u.setUsername(scan.nextLine().trim());
		System.out.println("Enter your password: ");
		u.setPassword(scan.nextLine().trim());
		AppState.state.setCurrentUser(u);
		UserDao.currentUserDao.createUser(u);
		
		return AccountRegistration.ar;
	}
	
	

}