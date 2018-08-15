package com.revature.beans;

import java.io.Serializable;

public abstract class Account implements Serializable {
	
	public abstract void deposit(double toAdd);
	public abstract double withdraw(double toTake);
	public abstract void viewBalance();
	public abstract void viewAccountHistory();
	

}
