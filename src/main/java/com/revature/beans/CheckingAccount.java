package com.revature.beans;

import java.sql.Time;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

public class CheckingAccount extends Account {
	
	private User u=new User();
	private long accountNumber;
	private double accountBalance;
	private List<Transaction> checkingHistory=new ArrayList<Transaction>();
	

	@Override
	public void deposit(double toAdd) {
		// TODO Auto-generated method stub
		accountBalance+=toAdd;
		Transaction t= new Transaction(accountBalance, toAdd + " added to checking on " + ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)), u.getUsername(), Instant.now());
		u.getTransactionHistory().add(t);
		this.checkingHistory.add(t);
		
	}
	@Override
	public double withdraw(double toTake) {
		if(accountBalance >= toTake) {
			accountBalance-=toTake;
			Transaction t= new Transaction(accountBalance, toTake + " removed from checking on " + ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)), u.getUsername(), Instant.now());
			u.getTransactionHistory().add(t);
			this.checkingHistory.add(t);
			return toTake;
		}
		else {
			System.out.println("Insufficient Funds.");
			return 0;
		}
	}
	@Override
	public void viewBalance() {
		// TODO Auto-generated method stub
		System.out.println("Current balance in checking account is: " + accountBalance);
		
	}
	@Override
	public void viewAccountHistory() {
		// TODO Auto-generated method stub
		for(Transaction t : checkingHistory) {
			System.out.println(t.getTransactionDescription());
		}
		
	}
	
	public void addFrom(User from, double amount) {
		
		accountBalance+=amount;
		Transaction t= new Transaction(accountBalance, from.getFirstName() + " "  + from.getLastName() + " added " + amount + " to checking on " + ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)), u.getUsername(), Instant.now());
		u.getTransactionHistory().add(t);
		this.checkingHistory.add(t);
		
		
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
		Transaction t= new Transaction(accountBalance, accountBalance + " added to checking on " + ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)), u.getUsername(), Instant.now());
		u.getTransactionHistory().add(t);
		this.checkingHistory.add(t);
	}
	public User getUser() {
		return u;
	}
	public void setUser(User u) {
		this.u = u;
	}
	
	
	
	
	

	

}
