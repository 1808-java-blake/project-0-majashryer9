package com.revature.beans;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

public class SavingsAccount extends Account {
	
	private User u;
	private long accountNumber;
	private double interestPercentage;
	private double accountBalance;
	private Instant instantNow; // set equal to Instant.now() when account created
	// getEpochSecond() to get long
	private List<Transaction> savingsHistory=new ArrayList<Transaction>();
	
	@Override
	public void deposit(double toAdd) {
		// TODO Auto-generated method stub
		accountBalance+=toAdd;
		Transaction t= new Transaction(accountBalance, toAdd + " added to savings on " + ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)), u.getUsername(), Instant.now());
		u.getTransactionHistory().add(t);
		this.savingsHistory.add(t);
		
	}
	@Override
	public double withdraw(double toTake) {
		if(accountBalance >= toTake) {
			accountBalance-=toTake;
			Transaction t= new Transaction(accountBalance, toTake + " removed from savings on " + ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)), u.getUsername(), Instant.now());
			u.getTransactionHistory().add(t);
			this.savingsHistory.add(t);
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
		System.out.println("Current balance in savings account is: " + accountBalance);
		
	}
	@Override
	public void viewAccountHistory() {
		// TODO Auto-generated method stub
		for(Transaction t : savingsHistory) {
			System.out.println(t.getTransactionDescription());
		}
		
	}
	
	public void addFrom(User from, double amount) {
		
		accountBalance+=amount;
		Transaction t= new Transaction(accountBalance, from.getFirstName() + " "  + from.getLastName() + " added " + amount + " to savings on " + ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)), u.getUsername(), Instant.now());
		u.getTransactionHistory().add(t);
		this.savingsHistory.add(t);
		
		
	}
	
	public void accruedInterest() {
		
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getInterestPercentage() {
		return interestPercentage;
	}
	public void setInterestPercentage(double interestPercentage) {
		this.interestPercentage = interestPercentage;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
		Transaction t= new Transaction(accountBalance, accountBalance + " added to savings on " + ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)), u.getUsername(), Instant.now());
		u.getTransactionHistory().add(t);
		this.savingsHistory.add(t);
		
	}
	public Instant getInstantNow() {
		return instantNow;
	}
	public void setInstantNow(Instant instantNow) {
		this.instantNow = instantNow;
	}
	public User getUser() {
		return u;
	}
	public void setUser(User u) {
		this.u = u;
	}
	
	
	
	

}
