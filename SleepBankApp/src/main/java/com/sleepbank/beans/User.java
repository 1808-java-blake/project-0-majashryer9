package com.sleepbank.beans;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private List<SleepAccount> myAccounts=new ArrayList<SleepAccount>();
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<SleepAccount> getMyAccounts() {
		return myAccounts;
	}

	public void setMyAccounts(List<SleepAccount> myAccounts) {
		this.myAccounts = myAccounts;
	}

	
	
	
	

}
