package com.ezequielvalencia.backend;

import com.ezequiel.ApiClient;
import com.ezequiel.ApiException;
import com.ezequiel.Configuration;
import com.ezequiel.api.GeoCacheApi;
import com.ezequiel.model.GeoCacheSubmission;
import com.ezequielvalencia.backend.db.DBHandler;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class BackendApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	DBHandler dbHandler;

	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
			"postgres:16-alpine"
	);

	@BeforeAll
	static void beforeAll() throws URISyntaxException, IOException {
		postgres.start();
	}

	@BeforeEach
	public void before() throws URISyntaxException, IOException {
		dbHandler.createTables();
	}

	@AfterAll
	static void afterAll() {
		postgres.stop();
	}

	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgres::getJdbcUrl);
		registry.add("spring.datasource.username", postgres::getUsername);
		registry.add("spring.datasource.password", postgres::getPassword);
	}

	@Test
	void contextLoads() {
	}

	@Test
	void uploadGeocache() throws ApiException {
		ApiClient defaultClient = Configuration.getDefaultApiClient();
		defaultClient.setPort(port);
		defaultClient.setHost("localhost");
		defaultClient.setScheme("http");
		GeoCacheApi geoCacheApi = new GeoCacheApi(defaultClient);

		// Empty, below char size, non ASCII
		GeoCacheSubmission[] failedSubmissions = {new GeoCacheSubmission(), new GeoCacheSubmission().name("j"), new GeoCacheSubmission().name("fff").note("ffff").secret("€")};

		for (GeoCacheSubmission sub : failedSubmissions){
			try{
				geoCacheApi.receiveSubmission(sub);
				throw new RuntimeException("Request didn't throw an error");
			} catch (ApiException e){
				Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), e.getCode(), "Submission can't be empty");
			}
		}

		GeoCacheSubmission validSubmission = new GeoCacheSubmission().name("Zeke").note("Hello world!").secret("Shhhh");
		geoCacheApi.receiveSubmission(validSubmission);


	}


}
