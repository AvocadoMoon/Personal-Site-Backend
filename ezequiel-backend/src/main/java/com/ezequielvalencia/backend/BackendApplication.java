package com.ezequielvalencia.backend;

import com.ezequielvalencia.backend.db.DBHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class BackendApplication {


	public static void main(String[] args) throws URISyntaxException, IOException {
		ApplicationContext applicationContext = SpringApplication.run(BackendApplication.class, args);

		// Automatically created from the settings within the 'application properties file'
//
		DBHandler dbHandler = new DBHandler(applicationContext.getBean(JdbcTemplate.class));
		dbHandler.createTables();
	}
	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
//		config.setAllowCredentials(true);
		// Don't do this in production, use a proper list  of allowed origins
		config.setAllowedOrigins(Collections.singletonList("*"));
		config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Access-Control-Allow-Origin"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS"));
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
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
