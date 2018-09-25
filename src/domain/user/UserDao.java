package domain.user;

import domain.user.User;
import domain.user.Login;

public interface UserDao {
	public int register(User c);
	public User validateUser(Login login);
}
