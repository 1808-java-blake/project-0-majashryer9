package com.sleepbank.screens;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Scanner;

import com.sleepbank.beans.SleepAccount;
import com.sleepbank.beans.Transaction;
import com.sleepbank.beans.User;
import com.sleepbank.daos.AccountDao;
import com.sleepbank.daos.TransactionDao;
import com.sleepbank.daos.UserDao;
import com.sleepbank.util.AppState;

public class TransferMoneyFromStandardHomepage implements Screen {
	
	public static final TransferMoneyFromStandardHomepage tmfs=new TransferMoneyFromStandardHomepage();
	private Scanner scan=new Scanner(System.in);
	User u=new User();

	@Override
	public Screen start() {
		
		u=AppState.state.getCurrentUser();
		System.out.println("Enter the username of the person you want to transfer sleep power to: Press 'e' to exit.");
		String username=scan.nextLine().trim();
		if(username.equals("e")) {
			return StandardAccountHomepage.sah;
		}
		User to=new User();
		to=UserDao.currentUserDao.adminGetUser(username);
		if(to==null) {
			System.out.println("Cannot find user with that username.");
			return tmfs;
		}
		if(to.getMyAccounts().size()==1) {
			// only has one account to deposit money into
			System.out.println("How much sleep power do you wish to deposit in " + to.getFirstname() + "'s account? ");
			String amount=scan.nextLine().trim();
			try {
				double withdrawn=u.getMyAccounts().get(0).withdraw(Double.parseDouble(amount));
				if(withdrawn==0) {
					return tmfs;
				}
				AccountDao.currentAccountDao.updateAccount(u.getMyAccounts().get(0));
				Transaction t1=new Transaction();
				t1.setAccountid(u.getMyAccounts().get(0).getAccountid());
				t1.setAmountSleepPowerInvolvedInTransaction(withdrawn);
				t1.setTransactionDescription("Withdrew " + withdrawn + " sleep power from " + u.getMyAccounts().get(0).getType() + " account on " + ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)));
				TransactionDao.currentTransactionDao.createTransaction(u.getMyAccounts().get(0).getAccountid(), t1);
				u.getMyAccounts().get(0).getTransactionHistory().add(t1);

				to.getMyAccounts().get(0).deposit(withdrawn);
				
				Transaction t=new Transaction();
				t.setAccountid(to.getMyAccounts().get(0).getAccountid());
				t.setAmountSleepPowerInvolvedInTransaction(withdrawn);
				t.setTransactionDescription(u.getFirstname() + " " + u.getLastname() + " deposited " + withdrawn + " sleep power into " + to.getMyAccounts().get(0).getType() + " account on " + ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)));
				TransactionDao.currentTransactionDao.createTransaction(to.getMyAccounts().get(0).getAccountid(), t);
				
				to.getMyAccounts().get(0).getTransactionHistory().add(t);
				AccountDao.currentAccountDao.updateAccount(to.getMyAccounts().get(0));
				
			} catch (Exception e) {
				
				System.out.println("Invalid input.");
				
			}
			
		}
		else {
			// has both
			System.out.println("Do you want to deposit sleep power in their (1) Standard Account or (2) Quality Boosting Account? ");
			String accountChoice=scan.nextLine();
			System.out.println("How much sleep power do you want to deposit? ");
			double withdrawn=0;
			try {
				withdrawn=u.getMyAccounts().get(0).withdraw(Double.parseDouble(scan.nextLine().trim()));
				if(withdrawn==0) {
					return tmfs;
				}
				AccountDao.currentAccountDao.updateAccount(u.getMyAccounts().get(0));
				Transaction t1=new Transaction();
				t1.setAccountid(u.getMyAccounts().get(0).getAccountid());
				t1.setAmountSleepPowerInvolvedInTransaction(withdrawn);
				t1.setTransactionDescription("Withdrew " + withdrawn + " sleep power from " + u.getMyAccounts().get(0).getType() + " account on " + ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)));
				TransactionDao.currentTransactionDao.createTransaction(u.getMyAccounts().get(0).getAccountid(), t1);
				u.getMyAccounts().get(0).getTransactionHistory().add(t1);
				
			} catch (Exception e) {
				System.out.println("Invalid input.");
			}
			if(accountChoice.equals("1")) {
				for(SleepAccount sa : to.getMyAccounts()) {
					if(sa.getType().equals("standard")) {
						sa.deposit(withdrawn);
						AccountDao.currentAccountDao.updateAccount(sa);
						Transaction t=new Transaction();
						t.setAccountid(sa.getAccountid());
						t.setAmountSleepPowerInvolvedInTransaction(withdrawn);
						t.setTransactionDescription(u.getFirstname() + " " + u.getLastname() + " deposited " + withdrawn + " sleep power into " + sa.getType() + " account on " + ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)));
						TransactionDao.currentTransactionDao.createTransaction(sa.getAccountid(), t);
						sa.getTransactionHistory().add(t);
						break;
					}
				}
				
			}
			else if(accountChoice.equals("2")) {
				for(SleepAccount sa : to.getMyAccounts()) {
					if(sa.getType().equals("quality boosting")) {
						AccountDao.currentAccountDao.updateAccount(sa);
						System.out.println(sa.getTotalSleepPower());
						Transaction t=new Transaction();
						t.setAccountid(sa.getAccountid());
						t.setAmountSleepPowerInvolvedInTransaction(withdrawn);
						t.setTransactionDescription(u.getFirstname() + " " + u.getLastname() + " deposited " + withdrawn + " sleep power into " + sa.getType() + " account on " + ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)));
						TransactionDao.currentTransactionDao.createTransaction(sa.getAccountid(), t);
						sa.getTransactionHistory().add(t);
						break;
					}
				}
				
			}
			else {
				System.out.println("Invalid input.");
			}	
		}
		return tmfs;
	}
	

}
