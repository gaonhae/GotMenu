package org.TUK.gotMenu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class GotMenuApplication {

	public static void main(String[] args) {
		SpringApplication.run(GotMenuApplication.class, args);
	}

}
