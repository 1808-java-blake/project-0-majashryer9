package com.revature.beans;

import java.io.Serializable;
import java.time.Instant;

public class Transaction implements Serializable {
	
	private double amount;
	private String transactionDescription;
	private String from;
	private Instant timeOf;
	
	public Transaction(double currentBalance, String transactionDescription, String from, Instant timeOf) {
		super();
		this.amount = amount;
		this.transactionDescription = transactionDescription;
		this.from = from;
		this.timeOf=timeOf;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTransactionDescription() {
		return transactionDescription;
	}

	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}
	
	
	
	

}
