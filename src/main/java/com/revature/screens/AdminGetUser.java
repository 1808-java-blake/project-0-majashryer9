package com.revature.screens;

import java.util.Scanner;

import com.revature.beans.User;
import com.revature.daos.UserDao;

public class AdminGetUser implements Screen {
	
	private UserDao ud=UserDao.currentUserDao;
	public static final AdminGetUser ag=new AdminGetUser();
	private Scanner scan=new Scanner(System.in);

	
	private AdminGetUser() {
		super();
		// TODO Auto-generated constructor stub
	}



	@Override
	public Screen start() {
		// TODO Auto-generated method stub
		System.out.println("Enter the username of the user whose account(s) you want to view: ");
		String username=scan.nextLine().trim();
		User u=new User();
		u=ud.adminGetUser(username);
		if(u!=null) {
			
			return new AdminHomepage(u);
			
		}
		
		return ag;
	}

}
