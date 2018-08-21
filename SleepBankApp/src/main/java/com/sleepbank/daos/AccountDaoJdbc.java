package com.sleepbank.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.PseudoColumnUsage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sleepbank.beans.SleepAccount;
import com.sleepbank.util.ConnectionUtil;

public class AccountDaoJdbc implements AccountDao {
	
	private ConnectionUtil cu=ConnectionUtil.cu;

	@Override
	public void createAccount(String username, SleepAccount sa) {
		
		try(Connection conn=cu.getConnection()) {
			
			PreparedStatement ps=conn.prepareStatement("INSERT INTO sleep_accounts(username, total_sleep_power, account_type) "
					+ "VALUES(?, ?, ?)");
			ps.setString(1, username);
			ps.setDouble(2, sa.getTotalSleepPower());
			ps.setString(3, sa.getType());
			ps.executeUpdate();
			PreparedStatement getAccountId=conn.prepareStatement("SELECT * FROM sleep_accounts WHERE username LIKE ?");
			getAccountId.setString(1, username);
			ResultSet rs=getAccountId.executeQuery();
			while(rs.next()) {
				String type=rs.getString("account_type");
				if(sa.getType().equals(type)) {
					sa.setAccountid(rs.getInt("accountid"));
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<SleepAccount> getAccountsByUsername(String username) {
		
		try(Connection conn=cu.getConnection()) {
			PreparedStatement ps=conn.prepareStatement("SELECT * FROM sleep_accounts WHERE username=?");
			ps.setString(1, username);
			ResultSet rs=ps.executeQuery();
			List<SleepAccount> sleepAccounts=new ArrayList<SleepAccount>();
			while(rs.next()) {
				SleepAccount s=new SleepAccount();
				s.setAccountid(rs.getInt("accountid"));
				s.setTotalSleepPower(rs.getDouble("total_sleep_power"));
				s.setType(rs.getString("account_type"));
				sleepAccounts.add(s);
				// Need to get transaction history
				if(TransactionDao.currentTransactionDao.getTransactionsByAccountId(s.getAccountid())!=null) {
					s.setTransactionHistory(TransactionDao.currentTransactionDao.getTransactionsByAccountId(s.getAccountid()));
				}
				
			}
			return sleepAccounts;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateAccount(SleepAccount sa) {
		// TODO Auto-generated method stub
		try(Connection conn=cu.getConnection()) {
			PreparedStatement ps=conn.prepareStatement("UPDATE sleep_accounts SET total_sleep_power=? WHERE accountid=?");
			ps.setDouble(1, sa.getTotalSleepPower());
			ps.setInt(2, sa.getAccountid());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	

}
