package com.revature.screens;

import java.util.Scanner;

public class AdminLogin implements Screen {
	
	public static final AdminLogin al=new AdminLogin();
	private final long ADMIN_CODE=123456789l;
	private Scanner scan=new Scanner(System.in);

	private AdminLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Screen start() {
		System.out.println("Enter admin code: (For Blake- It's 123456789) ");
		String code=scan.nextLine();
		try {
			long toLong=Long.parseLong(code);
			if(toLong==ADMIN_CODE) {
				return AdminGetUser.ag;
			}
			else {
				System.out.println("Invalid code.");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Invalid input.");
		}
		return al;
	}
	
	

}
