package i.valeriytimakov.usersegmentation.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import i.valeriytimakov.usersegmentation.model.User;
import i.valeriytimakov.usersegmentation.model.UserType;

@Repository
public class UsersRepository {
	
	@Autowired(required = true)
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	public List<User> getUsers(UserType userType) {
		String idTypePattern = "";
		switch (userType) {
			case EVEN:
				idTypePattern = "13579";
				break;
			case ODD:
				idTypePattern = "02468";
				break;
			case INVALID:
				idTypePattern = "a-zA-Z";
				break;
		}
		if (!idTypePattern.isEmpty()) {
			idTypePattern = " WHERE id REGEXP '.*[" + idTypePattern + "]$'";
		}
		
		return jdbcTemplate.query("SELECT id FROM users" + idTypePattern + ";", new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet resultSet, int i) throws SQLException {
				return new User(resultSet.getString("id"));
			}			
		});
	}
	
	public void add(List<User> users) {
		jdbcTemplate.batchUpdate("INSERT IGNORE INTO users (id) VALUES(?);", new BatchPreparedStatementSetter() {		
			
			@Override
			public void setValues(PreparedStatement statement, int i) throws SQLException {
				statement.setString(1, users.get(i).getId());
			}
			
			@Override
			public int getBatchSize() {
				return users.size();
			}
			
		});
	}

}
