package i.valeriytimakov.usersegmentation.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import i.valeriytimakov.usersegmentation.model.User;

@Component
public class FlatReaderUsersParser implements UsersParser {
	
	private FlatFileItemReader<User> fileReader;
	
	public FlatReaderUsersParser() {		
		fileReader =new FlatFileItemReader<>();
		fileReader.setLinesToSkip(1);
		
		DefaultLineMapper<User> lineMapper = new DefaultLineMapper<>();
		lineMapper.setLineTokenizer(new DelimitedLineTokenizer());
		lineMapper.setFieldSetMapper(new FieldSetMapper<User>() {		
			
			@Override
			public User mapFieldSet(FieldSet fieldSet) throws BindException {
				return new User(fieldSet.readString(0));
			}
			
		});
		fileReader.setLineMapper(lineMapper);
		
	}
	
	@Override
	public List<User> parse(Resource file) throws Exception {
		fileReader.setResource(file);
		try {
			List<User> users = new ArrayList<>();
			User user;
			fileReader.open(new ExecutionContext());
			while ((user = fileReader.read()) != null) {
				users.add(user);
			}
			return users;
		} finally {
			fileReader.close();
		}
		
	}
	

}
