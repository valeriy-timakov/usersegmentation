package com.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.core.io.Resource;

import i.valeriytimakov.usersegmentation.model.User;
import i.valeriytimakov.usersegmentation.model.UserType;
import i.valeriytimakov.usersegmentation.persistence.UsersRepository;
import i.valeriytimakov.usersegmentation.utils.UsersParser;
import i.valeriytimakov.usersegmentation.web.UsersController;

public class UsersControllerTests {
	
	class TestUsersRepository implements UsersRepository {
		
		private List<User> users;
		
		@Override
		public List<User> getUsers(UserType userType) {
			return null;
		}
		
		@Override
		public void add(List<User> users) {
			this.users = users;
		}

		public List<User> getAllUsers() {
			return users;
		}
	}
	
	@Test
	public void controllerShoudAddObjectsToRepository() {
		TestUsersRepository repository = new TestUsersRepository();
		UsersParser parser = new UsersParser() {
			
			@Override
			public List<User> parse(Resource file) throws Exception {
				ArrayList<User> list = new ArrayList<>();
				list.add(new User("1"));
				list.add(new User("2"));
				return list;
			}
		};
		UsersController controller = new UsersController(repository, parser);
		
		controller.handleUpload(null);
		assertTrue(repository.getAllUsers().contains(new User("1")));
		assertTrue(repository.getAllUsers().contains(new User("2")));
	}
	
	@Test
	public void controllerShoudReturnAllRepositoryObjects() {
		UsersRepository repository = new UsersRepository() {
			
			@Override
			public List<User> getUsers(UserType userType) {
				ArrayList<User> list = new ArrayList<>();
				list.add(new User("1"));
				list.add(new User("2"));
				return list;
			}
			
			@Override
			public void add(List<User> users) {
				
			}
		};
		UsersParser parser = new UsersParser() {
			
			@Override
			public List<User> parse(Resource file) throws Exception {
				return null;
			}
		};
		UsersController controller = new UsersController(repository, parser);
		
		List<User> foundUsers = controller.users(UserType.EVEN);
		assertTrue(foundUsers.contains(new User("1")));
		assertTrue(foundUsers.contains(new User("2")));
	}

}
