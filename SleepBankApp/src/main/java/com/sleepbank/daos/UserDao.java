package com.sleepbank.daos;

import com.sleepbank.beans.User;

public interface UserDao {
	
	public static final UserDao currentUserDao=new UserDaoJdbc();
	
	void createUser(User u);
	User findByUsernameAndPassword(String username, String password);
	User adminGetUser(String username);

}
