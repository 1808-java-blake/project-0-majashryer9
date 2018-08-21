package com.sleepbank.screens;

import java.util.Scanner;

public class AdminLogin implements Screen{
	
	public static final AdminLogin al=new AdminLogin();
	private final int ADMIN_CODE=123456789;
	private Scanner scan=new Scanner(System.in);

	private AdminLogin() {
	}

	public Screen start() {
		System.out.println("Enter the admin code to log in as admin: ");
		String input=scan.nextLine();
		try {
			if(Integer.parseInt(input)==ADMIN_CODE) {
				return AdminHomepage.ah;
			}
			else {
				System.out.println("Incorrect code.");
			}
			
		} catch (Exception e) {
			System.out.println("Invalid input.");
		}
		return al;
	}

}
