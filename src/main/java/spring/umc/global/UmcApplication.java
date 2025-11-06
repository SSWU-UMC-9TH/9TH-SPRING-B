package spring.umc.global;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
//@SpringBootApplication
@SpringBootApplication(scanBasePackages = "spring.umc")
@EnableJpaRepositories(basePackages = "spring.umc.domain")
@EntityScan("spring.umc.domain")
public class UmcApplication {

	public static void main(String[] args) {
		SpringApplication.run(UmcApplication.class, args);
	}

}
