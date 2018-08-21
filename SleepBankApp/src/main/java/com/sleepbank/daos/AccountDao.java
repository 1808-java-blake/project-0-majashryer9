package com.sleepbank.daos;

import java.util.List;

import com.sleepbank.beans.SleepAccount;

public interface AccountDao {
	
	public static final AccountDao currentAccountDao=new AccountDaoJdbc();
	
	void createAccount(String username, SleepAccount sa);
	List<SleepAccount> getAccountsByUsername(String username);
	void updateAccount(SleepAccount sa);

}
