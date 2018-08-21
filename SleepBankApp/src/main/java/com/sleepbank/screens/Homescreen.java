package com.sleepbank.screens;

import java.util.Scanner;

public class Homescreen implements Screen {
	
	public static final Homescreen hs=new Homescreen();
	private Scanner scan=new Scanner(System.in);
	

	private Homescreen() {
		
	}

	public Screen start() {
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
					
					return UserRegistration.ur;
				
				case 3:
					//return new AdminPage;
					return AdminLogin.al;
					
				}
			}
			else {
				System.out.println("Invalid input.");
			}
		
		return hs;
	}

}