package com.sleepbank.beans;

public class Transaction {
	
	private int accountid;
	private double amountSleepPowerInvolvedInTransaction;
	private String transactionDescription;
	
	public int getAccountid() {
		return accountid;
	}
	
	public void displayTransaction() {
		
		System.out.println(transactionDescription);
		
	}
	
	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}
	public double getAmountSleepPowerInvolvedInTransaction() {
		return amountSleepPowerInvolvedInTransaction;
	}
	public void setAmountSleepPowerInvolvedInTransaction(double amountSleepPowerInvolvedInTransaction) {
		this.amountSleepPowerInvolvedInTransaction = amountSleepPowerInvolvedInTransaction;
	}
	public String getTransactionDescription() {
		return transactionDescription;
	}
	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
	}
	
	
	
	

}
