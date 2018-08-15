package com.revature.screens;

import java.util.Scanner;

import com.revature.beans.User;
import com.revature.daos.UserDao;

public class UserRegistration implements Screen {
	
	public static final UserRegistration urs=new UserRegistration();
	private Scanner scan=new Scanner(System.in);
	public UserDao ud=UserDao.currentUserDao;
	

	public UserRegistration() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Screen start() {
		// TODO Auto-generated method stub
		
		User u=new User();
		System.out.println("Enter your first name: ");
		u.setFirstName(scan.nextLine().trim());
		System.out.println("Enter your last name: ");
		u.setLastName(scan.nextLine().trim());
		System.out.println("Enter your username: ");
		String username=scan.nextLine().trim();
		u.setUsername(username);
		System.out.println("Enter your password: ");
		String password=scan.nextLine().trim();
		u.setPassword(password);
		
		if(ud.createUser(u)) {
			System.out.println("Excellent!");
			return new Registration(username, password);
		}
		else {
			return UserRegistration.urs;
		}
	}
	

}
