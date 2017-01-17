package i.valeriytimakov.usersegmentation.persistence;

import java.util.List;

import i.valeriytimakov.usersegmentation.model.User;
import i.valeriytimakov.usersegmentation.model.UserType;

public interface UsersRepository {
	List<User> getUsers(UserType userType);
	void add(List<User> users);
}
