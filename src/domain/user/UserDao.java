package domain.user;

import java.util.ArrayList;

import domain.user.User;

public interface UserDao {
	int register(User c);
	int login(User c);
	ArrayList display();
	ArrayList<User> searchUser(String input);
	void uploadUser(String[] input);
}
