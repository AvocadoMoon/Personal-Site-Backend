package com.ezequielvalencia.backend;

import com.ezequielvalencia.backend.db.DBHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootApplication
public class BackendApplication {


	public static void main(String[] args) throws URISyntaxException, IOException {
		ApplicationContext applicationContext = SpringApplication.run(BackendApplication.class, args);

		// Automatically created from the settings within the 'application properties file'
		DBHandler dbHandler = new DBHandler(applicationContext.getBean(JdbcTemplate.class));
		dbHandler.createTables();
	}
//	@Bean
//	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//		return args -> {
//			System.out.println("Let's inspect the beans provided by Spring Boot:");
//			String[] beanNames = ctx.getBeanDefinitionNames();
//			Arrays.sort(beanNames);
//			for (String beanName : beanNames) {
//				System.out.println(beanName);
//			}
//		};
//	}

}
