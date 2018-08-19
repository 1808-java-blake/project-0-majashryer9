package com.revature.beans;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

public class SavingsAccount extends Account {
	
	private User u;
	private long accountNumber;
	private double interestRate;
	private double accountBalance;
	private Instant lastLogin; // set equal to Instant.now() when last logged in
	// getEpochSecond() to get long
	private List<Transaction> savingsHistory=new ArrayList<Transaction>();
	private double totalInterestAccrued=0;
	
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
	
	private double millisecondsToYears(long ms) {
		return ms/(1000*60*60*24*365);
	}
	
	//compounded continuously, credited every time you login
	public void accrueInterest() {
		
		if(Instant.now().toEpochMilli()-lastLogin.toEpochMilli() > 86400000) {
			long changeInTime=Instant.now().getEpochSecond()-this.lastLogin.getEpochSecond();
			System.out.println(changeInTime);
			double principalInterest=this.accountBalance;
			double time=millisecondsToYears(changeInTime);
			double amountToAdd=principalInterest*Math.exp(interestRate*time);
			System.out.println("Added from interest " + amountToAdd);
			totalInterestAccrued+=amountToAdd-principalInterest;
			this.accountBalance=amountToAdd;
		}
		
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public double getInterestRate() {
		return interestRate;
	}
	
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
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
	
	public Instant getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Instant lastLogin) {
		this.lastLogin = lastLogin;
	}
	public User getUser() {
		return u;
	}
	public void setUser(User u) {
		this.u = u;
	}
	
	
	
	

}
