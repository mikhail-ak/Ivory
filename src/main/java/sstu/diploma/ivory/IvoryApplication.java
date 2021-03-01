package sstu.diploma.ivory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class IvoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(IvoryApplication.class, args);
	}
}
