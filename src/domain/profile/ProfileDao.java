package domain.profile;

import java.util.ArrayList;

public interface ProfileDao {
	int modifyProfile(Profile file);
	Profile getProfile(int userid);
	ArrayList display();
}
