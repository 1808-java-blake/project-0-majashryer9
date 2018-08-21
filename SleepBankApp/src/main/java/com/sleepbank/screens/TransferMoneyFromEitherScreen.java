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

public class TransferMoneyFromEitherScreen implements Screen {
	
	public static final TransferMoneyFromEitherScreen tmfe=new TransferMoneyFromEitherScreen();
	private Scanner scan=new Scanner(System.in);
	private User u=new User();
	
	private TransferMoneyFromEitherScreen() {
	}

	@Override
	public Screen start() {
		
		u=AppState.state.getCurrentUser();
		System.out.println("Enter the username of the person you want to transfer sleep power to: Press 'e' to exit.");
		String username=scan.nextLine().trim();
		if(username.equals("e")) {
			return StandardAndQualityHomepage.saqh;
		}
		User to=new User();
		to=UserDao.currentUserDao.adminGetUser(username);
		if(to==null) {
			System.out.println("Cannot find user with that username.");
			return tmfe;
		}
		System.out.println("Do you want to transfer sleep power from your (1) Standard Account or (2) Quality Boosting Account?");
		String withdrawalAccount=scan.nextLine();
		SleepAccount toTakeMoneyFrom=new SleepAccount();
		if(withdrawalAccount.equals("1")) {
			for(SleepAccount sa : u.getMyAccounts()) {
				if(sa.getType().equals("standard")) {
					toTakeMoneyFrom=sa;
					break;
				}
			}
		}
		else if(withdrawalAccount.equals("2")) {
			for(SleepAccount sa : u.getMyAccounts()) {
				if(sa.getType().equals("quality boosting")) {
					toTakeMoneyFrom=sa;
					break;
				}
			}
			
		}
		else {
			System.out.println("Invalid input.");
			return tmfe;
		}
		if(to.getMyAccounts().size()==1) {
			// only has one account to deposit money into
			System.out.println("How much sleep power do you wish to deposit in " + to.getFirstname() + "'s account? ");
			String amount=scan.nextLine().trim();
			try {
				double withdrawn=toTakeMoneyFrom.withdraw(Double.parseDouble(amount));
				if(withdrawn==0) {
					return tmfe;
				}
				AccountDao.currentAccountDao.updateAccount(toTakeMoneyFrom);
				Transaction t1=new Transaction();
				t1.setAccountid(toTakeMoneyFrom.getAccountid());
				t1.setAmountSleepPowerInvolvedInTransaction(withdrawn);
				t1.setTransactionDescription("Withdrew " + withdrawn + " sleep power from " + toTakeMoneyFrom.getType() + " account on " + ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)));
				TransactionDao.currentTransactionDao.createTransaction(toTakeMoneyFrom.getAccountid(), t1);
				toTakeMoneyFrom.getTransactionHistory().add(t1);
				
				to.getMyAccounts().get(0).deposit(withdrawn);
				AccountDao.currentAccountDao.updateAccount(to.getMyAccounts().get(0));
				Transaction t=new Transaction();
				t.setAccountid(to.getMyAccounts().get(0).getAccountid());
				t.setAmountSleepPowerInvolvedInTransaction(withdrawn);
				t.setTransactionDescription(u.getFirstname() + " " + u.getLastname() + " deposited " + withdrawn + " sleep power into " + to.getMyAccounts().get(0).getType() + " account on " + ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)));
				TransactionDao.currentTransactionDao.createTransaction(to.getMyAccounts().get(0).getAccountid(), t);
				to.getMyAccounts().get(0).getTransactionHistory().add(t);
				
			} catch (Exception e) {
				
				System.out.println("Invalid input.");
				
			}
			
		}
		else {
			// has both
			System.out.println("Do you want to deposit sleep power in their (1) Standard Account or (2) Quality Boosting Account?");
			String accountChoice=scan.nextLine();
			System.out.println("How much sleep power do you want to deposit? ");
			double withdrawn=0;
			try {
				withdrawn=toTakeMoneyFrom.withdraw(Double.parseDouble(scan.nextLine().trim()));
				if(withdrawn==0) {
					return tmfe;
				}
				AccountDao.currentAccountDao.updateAccount(toTakeMoneyFrom);
				Transaction t1=new Transaction();
				t1.setAccountid(toTakeMoneyFrom.getAccountid());
				t1.setAmountSleepPowerInvolvedInTransaction(withdrawn);
				t1.setTransactionDescription("Withdrew " + withdrawn + " sleep power from " + toTakeMoneyFrom.getType() + " account on " + ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)));
				TransactionDao.currentTransactionDao.createTransaction(toTakeMoneyFrom.getAccountid(), t1);
				toTakeMoneyFrom.getTransactionHistory().add(t1);
				
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
			else {
				System.out.println("Invalid input");
			}	
		}
		return tmfe;
	}

}
