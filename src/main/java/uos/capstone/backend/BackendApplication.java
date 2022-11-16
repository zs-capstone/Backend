package uos.capstone.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class BackendApplication {
	// 시작
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
