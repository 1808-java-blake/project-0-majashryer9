package com.sleepbank.screens;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Scanner;

import com.sleepbank.beans.Transaction;
import com.sleepbank.beans.User;
import com.sleepbank.daos.AccountDao;
import com.sleepbank.daos.TransactionDao;
import com.sleepbank.util.AppState;

public class WithdrawQualityScreen implements Screen {
	
	public static final WithdrawQualityScreen wqs=new WithdrawQualityScreen();
	private Scanner scan=new Scanner(System.in);
	private User u=new User();
	@Override
	public Screen start() {
		// TODO Auto-generated method stub
		u=AppState.state.getCurrentUser();
		System.out.println("How many hours of sleep did you get last night? ");
		String hoursSlept=scan.nextLine();
		System.out.println("How many hours of sleep do you wish you got last night? ");
		String hoursWanted=scan.nextLine();
		System.out.println("Rate the quality of your sleep: ");
		System.out.println("(1) Tossed and turned more than towels in a dryer");
		System.out.println("(2) Wasn't the best, wasn't the worst");
		System.out.println("(3) Very average");
		System.out.println("(4) A good night's sleep");
		System.out.println("(5) Could have slept through a Led Zeppelin concert");
		String qualityWanted=scan.nextLine();
		double sleepPower=0;
		try {
			sleepPower=(Double.parseDouble(hoursWanted)-Double.parseDouble(hoursSlept))*Integer.parseInt(qualityWanted);
			if(sleepPower < 0) {
				System.out.println("Why would you want fewer hours of sleep than you got? Let's try again.");
				return wqs;
			}
;		} catch (Exception e) {
			System.out.println("Invalid input.");
			return wqs;
		}
		System.out.println("To have your desired amount of sleep power, you must withdraw " + sleepPower + " sleep power.");
		System.out.println("How much sleep power do you want to withdraw? ");
		String toWithdraw=scan.nextLine();
		try {
			
			u.getMyAccounts().get(0).withdraw(Double.parseDouble(toWithdraw)); // make transaction
			Transaction t=new Transaction();
			t.setAccountid(u.getMyAccounts().get(0).getAccountid());
			t.setAmountSleepPowerInvolvedInTransaction(Double.parseDouble(toWithdraw));
			t.setTransactionDescription("Withdrew " + Double.parseDouble(toWithdraw) + " sleep power from " + u.getMyAccounts().get(0).getType() + " account on " + ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)));
			TransactionDao.currentTransactionDao.createTransaction(u.getMyAccounts().get(0).getAccountid(), t);
			u.getMyAccounts().get(0).getTransactionHistory().add(t);
			// update account
			AccountDao.currentAccountDao.updateAccount(u.getMyAccounts().get(0));
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Invalid input.");
			return wqs;
		}
		return QualityBoostingHomepage.qbh;
	}
	

}
