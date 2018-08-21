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
import com.sleepbank.util.AppState;

public class DepositStandardScreen implements Screen {
	
	public static final DepositStandardScreen dss=new DepositStandardScreen();
	private Scanner scan=new Scanner(System.in);
	private User u=new User();

	private DepositStandardScreen() {
		
	}

	@Override
	public Screen start() {
		u=AppState.state.getCurrentUser();
		SleepAccount toDepositIn=u.getMyAccounts().get(0);
		System.out.println("How many hours of sleep did you get last night? Friendly reminder to be truthful.");
		System.out.println("If you attempt to deposit more sleep power than you have stored in your system, our sleep power");
		System.out.println("extractors will put you to sleep until you have enough to make the full deposit. This can be quite");
		System.out.println("inconvenient if you have to be somewhere later.");
		String hours=scan.nextLine();
		System.out.println("Rate the quality of your sleep: ");
		System.out.println("(1) Tossed and turned more than towels in a dryer");
		System.out.println("(2) Wasn't the best, wasn't the worst");
		System.out.println("(3) Very average");
		System.out.println("(4) A good night's sleep");
		System.out.println("(5) Could have slept through a Led Zeppelin concert");
		String quality=scan.nextLine();
		try {
			double sleepPower=Double.parseDouble(hours)*Integer.parseInt(quality);
			System.out.println("The amount of sleep power you acquired last night: " + sleepPower);
			System.out.println("How much do you want to deposit? ");
			double toDeposit=Double.parseDouble(scan.nextLine());
			toDepositIn.deposit(toDeposit);
			// make transaction
			Transaction t=new Transaction();
			t.setAccountid(toDepositIn.getAccountid());
			t.setAmountSleepPowerInvolvedInTransaction(toDeposit);
			t.setTransactionDescription("Deposited " + toDeposit + " sleep power into " + toDepositIn.getType() + " account on " + ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)));
			TransactionDao.currentTransactionDao.createTransaction(toDepositIn.getAccountid(), t);
			toDepositIn.getTransactionHistory().add(t);
			
			// update account
			AccountDao.currentAccountDao.updateAccount(toDepositIn);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Invalid input.");
			return dss;
		}
		
		return StandardAccountHomepage.sah;
	}
	

}
