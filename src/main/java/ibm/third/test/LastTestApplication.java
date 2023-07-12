package ibm.third.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class LastTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(LastTestApplication.class, args);
	}

}
