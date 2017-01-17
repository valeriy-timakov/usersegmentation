package i.valeriytimakov.usersegmentation.utils;

import java.util.List;

import org.springframework.core.io.Resource;

import i.valeriytimakov.usersegmentation.model.User;

public interface UsersParser {
	List<User> parse(Resource file) throws Exception;	
}
