package i.valeriytimakov.usersegmentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class UserSegmentationApplication implements CommandLineRunner {
	
	@Autowired(required=true)
	@Qualifier("jdbcTemplate")
	JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(UserSegmentationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS users (id VARCHAR(255) NOT NULL PRIMARY KEY);");
	}
}
