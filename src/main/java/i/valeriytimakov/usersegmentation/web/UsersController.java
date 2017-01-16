package i.valeriytimakov.usersegmentation.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import i.valeriytimakov.usersegmentation.model.MultipartResourceWrapper;
import i.valeriytimakov.usersegmentation.model.OperationResult;
import i.valeriytimakov.usersegmentation.model.User;
import i.valeriytimakov.usersegmentation.model.UserType;
import i.valeriytimakov.usersegmentation.persistence.UsersRepository;
import i.valeriytimakov.usersegmentation.utils.UsersParser;

@RestController
public class UsersController {
	
	private static final Logger logger = LoggerFactory.getLogger(UsersController.class);
	
	@Autowired(required=true)
	UsersRepository usersRepository;
	
	@Autowired(required=true)
	UsersParser usersParser;
	
	@RequestMapping(value="/users/{type}", method=RequestMethod.GET)
	public List<User> users(@PathVariable(name="type") UserType userType) {
		return usersRepository.getUsers(userType);
	}
	
	@RequestMapping(value="/users/add", method=RequestMethod.POST)
	public OperationResult handleUpload(@RequestParam("file") MultipartFile file) {		
		try {
			List<User> users = usersParser.parse(new MultipartResourceWrapper(file));
			usersRepository.add(users);
			return new OperationResult(true);
		} catch (Exception e) {
			logger.error("Add users error!", e);
			return new OperationResult(false);
		}
	}
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(UserType.class, new UserTypeEnumConverter());
	}

}
