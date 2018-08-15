package com.revature.screens;

import java.util.Scanner;

public class Homepage implements Screen {
	
	private Scanner scan=new Scanner(System.in);
	public static final Homepage hp=new Homepage();
	
	private Homepage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Screen start() {
		// TODO Auto-generated method stub
		System.out.println("Select an option: ");
		System.out.println("(1) to Login");
		System.out.println("(2) to Register");
		System.out.println("(3) for Admin");
		String input=scan.nextLine();
			if(input.equals("1") || input.equals("2") || input.equals("3")) {
				switch (Integer.parseInt(input)) {
				case 1: // return login screen
					
					return Login.l;
				
				case 2: // return registration screen
					
					return UserRegistration.urs;
				
				case 3:
					//return new AdminPage;
					return AdminLogin.al;
					
				}
			}
			else {
				System.out.println("Invalid input.");
			}
		
		return hp;
	}
	

}
