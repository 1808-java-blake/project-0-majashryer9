package com.sleepbank.beans;

import java.util.ArrayList;
import java.util.List;

public class SleepAccount {
	
	private int accountid;
	private double totalSleepPower;
	private String type;
	private List<Transaction> transactionHistory=new ArrayList<Transaction>();
	
	public void deposit(double sleepPowerToAdd) {
		
		totalSleepPower+=sleepPowerToAdd;
		
	}
	
	public double withdraw(double sleepPowerToTake) {
		if(totalSleepPower-sleepPowerToTake < 0) {
			System.out.println("Insufficient Funds");
			return 0;
		}
		totalSleepPower-=sleepPowerToTake;
		return sleepPowerToTake;
	}
	
	public void viewAccountBalance() {
		System.out.println("Current " + type + " account balance: " + totalSleepPower);
	}
	
	public void viewTransactionHistory() {
		
		for(Transaction t : transactionHistory) {
			t.displayTransaction();
		}	
	}
	
	public double getTotalSleepPower() {
		return totalSleepPower;
	}
	public void setTotalSleepPower(double totalSleepPower) {
		this.totalSleepPower = totalSleepPower;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getAccountid() {
		return accountid;
	}
	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}
	public List<Transaction> getTransactionHistory() {
		return transactionHistory;
	}
	public void setTransactionHistory(List<Transaction> transactionHistory) {
		this.transactionHistory = transactionHistory;
	}
	
	
	
	
	

}
