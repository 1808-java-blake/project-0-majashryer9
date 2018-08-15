package com.revature.daos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.revature.beans.User;

public class UserSerializer implements UserDao {
	
	public static final UserSerializer us=new UserSerializer();
	

	private UserSerializer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean createUser(User u) {
		// TODO Auto-generated method stub
		File newFolder=new File("src/main/resources/users");
		if(!newFolder.exists()) {
			newFolder.mkdirs();
		}
		File newUser=new File("src/main/resources/users/" + u.getUsername() + ".txt");
		if(!newUser.exists()) {
			
			try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(newUser))) {
				oos.writeObject(u);
				return true;
			} catch (FileNotFoundException e) {
				
			} catch (IOException e) {
				
			}
		}
		else {
			System.out.println("Unavailable username.");
		}
		return false;
		
	}
	
	public User adminGetUser(String username) {
		
		try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream("src/main/resources/users/" + username + ".txt"))) {
			
			return (User)(ois.readObject());
			
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Cannot find user with that username.");
		}
		return null;
		
	}
	

	public User getUser(String username, String password) {
		
		try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream("src/main/resources/users/" + username + ".txt"))) {
			User u=(User)(ois.readObject());
			if(u.getPassword().equals(password)) {
				return u;
			}
			System.out.println("Incorrect password.");
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Cannot find user with that username.");
		}
		return null;
	}

	public void updateUser(User u) {
		// TODO Auto-generated method stub
		try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("src/main/resources/users/" + u.getUsername() + ".txt"));) {
			oos.writeObject(u);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void deleteUser(User u) {
		// TODO Auto-generated method stub
		
	}

}
