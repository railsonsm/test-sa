package sa.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TestSaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestSaApplication.class, args);
	}

}
