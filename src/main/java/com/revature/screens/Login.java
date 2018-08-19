package com.revature.screens;

import java.util.Scanner;

import com.revature.beans.User;
import com.revature.daos.UserDao;
import com.revature.util.AppState;

public class Login implements Screen {
	
	public static final Login l=new Login();
	private UserDao ud=UserDao.currentUserDao;
	private Scanner scan=new Scanner(System.in);
	

	private Login() {
		super();
		// TODO Auto-generated constructor stub
	}



	@Override
	public Screen start() {
		// TODO Auto-generated method stub
		System.out.println("Enter username: ");
		String username=scan.nextLine();
		System.out.println("Enter password: ");
		String password=scan.nextLine();
		User u=new User();
		u=ud.getUser(username, password);
		AppState.state.setCurrentUser(u);
		if(u==null) {
			
			return Homepage.hp;
		}
		return UserHomepage.usehome;
	}

}
