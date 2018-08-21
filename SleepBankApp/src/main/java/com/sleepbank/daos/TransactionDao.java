package com.sleepbank.daos;

import java.util.List;

import com.sleepbank.beans.SleepAccount;
import com.sleepbank.beans.Transaction;

public interface TransactionDao {
	
public static final TransactionDao currentTransactionDao=new TransactionDaoJdbc();
	
	void createTransaction(int accountid, Transaction t);
	List<Transaction> getTransactionsByAccountId(int accountid);

}