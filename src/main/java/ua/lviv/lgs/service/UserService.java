package ua.lviv.lgs.service;

import ua.lviv.lgs.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
	List<User> userList = new ArrayList<>();
	private static UserService userService;
	
	
	private UserService() {
		userList.add(new User("admin","admin","admin","admin"));
	}
	
	public static UserService getUserService() {
		if (userService == null) {
			userService = new UserService();
		}
		return userService;
	}
	
	public List<User> getList() {
		return userList;
	}
	
	public void saveUser(User user) {
		userList.add(user);
	}
	
	public User getUser(String email) {
		return userList.stream().filter(x -> x.getEmail().equals(email)).findFirst().get();
	}
}
