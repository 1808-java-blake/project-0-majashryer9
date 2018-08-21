package com.sleepbank.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sleepbank.beans.Transaction;
import com.sleepbank.util.ConnectionUtil;

public class TransactionDaoJdbc implements TransactionDao {
	
	private ConnectionUtil cu=ConnectionUtil.cu;

	@Override
	public void createTransaction(int accountid, Transaction t) {
		// TODO Auto-generated method stub
		try(Connection conn=cu.getConnection()) {
			PreparedStatement ps=conn.prepareStatement("INSERT INTO transactions(accountid, amount_sleep_power_involved_in_transaction, transaction_description)"
					+ "VALUES(?, ?, ?)");
			ps.setInt(1, accountid);
			ps.setDouble(2, t.getAmountSleepPowerInvolvedInTransaction());
			ps.setString(3, t.getTransactionDescription());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public List<Transaction> getTransactionsByAccountId(int accountid) {
		
		try(Connection conn=cu.getConnection()) {
			PreparedStatement ps=conn.prepareStatement("SELECT * FROM transactions WHERE accountid=?");
			ps.setInt(1, accountid);
			ResultSet rs=ps.executeQuery();
			List<Transaction> allTransactions=new ArrayList<Transaction>();
			while(rs.next()) {
				Transaction t=new Transaction();
				t.setAccountid(accountid);
				t.setAmountSleepPowerInvolvedInTransaction(rs.getDouble("amount_sleep_power_involved_in_transaction"));
				t.setTransactionDescription(rs.getString("transaction_description"));
				allTransactions.add(t);
			}
			
			return allTransactions;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
