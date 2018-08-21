package com.sleepbank.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sleepbank.beans.SleepAccount;
import com.sleepbank.beans.User;
import com.sleepbank.util.ConnectionUtil;

public class UserDaoJdbc implements UserDao {
	
	private ConnectionUtil cu=ConnectionUtil.cu;
	
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createUser(User u) {
		
		try(Connection conn=cu.getConnection()) {
			PreparedStatement ps=conn.prepareStatement("INSERT INTO users(firstname, lastname, username, password) "
					+ "VALUES(?, ?, ?, ?)");
			ps.setString(1, u.getFirstname());
			ps.setString(2, u.getLastname());
			ps.setString(3, u.getUsername());
			ps.setString(4, u.getPassword());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public User findByUsernameAndPassword(String username, String password) {
		
		try(Connection conn=cu.getConnection()) {
			User u=new User();
			PreparedStatement ps=conn.prepareStatement("SELECT * FROM users WHERE username=?");
			ps.setString(1, username);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				u.setFirstname(rs.getString("firstname"));
				u.setLastname(rs.getString("lastname"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
			}
			List<SleepAccount> accounts=AccountDao.currentAccountDao.getAccountsByUsername(username);
			u.setMyAccounts(accounts);
			
			if(password.equals(u.getPassword())) {
				return u;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public User adminGetUser(String username) {
		try(Connection conn=cu.getConnection()) {
			User u=new User();
			PreparedStatement ps=conn.prepareStatement("SELECT * FROM users WHERE username=?");
			ps.setString(1, username);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				u.setFirstname(rs.getString("firstname"));
				u.setLastname(rs.getString("lastname"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
			}
			List<SleepAccount> accounts=AccountDao.currentAccountDao.getAccountsByUsername(username);
			u.setMyAccounts(accounts);
			
			if(u.getUsername()!=null) {
				return u;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
