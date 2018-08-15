package com.revature.daos;

import com.revature.beans.User;

public interface UserDao {
	
	public static final UserDao currentUserDao=UserSerializer.us;
	
	boolean createUser(User u);
	User getUser(String username, String password);
	User adminGetUser(String username);
	void updateUser(User u);
	void deleteUser(User u);

}
