package com.ezequielvalencia.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class OpenAPIGenerator implements CommandLineRunner {
    private static final String OPENAPI_YAML_URL = "http://localhost:8080/v3/api-docs.yaml"; // OpenAPI YAML URL
    private static final String OUTPUT_PATH = "target/openapi.yaml"; // Target folder

    @Override
    public void run(String... args) throws Exception {
//        try {
//            Thread.sleep(3000); // Wait for server to fully start (optional)
//            RestTemplate restTemplate = new RestTemplate();
//            String yamlSpec = restTemplate.getForObject(OPENAPI_YAML_URL, String.class);
//
//            File file = new File(OUTPUT_PATH);
//            file.getParentFile().mkdirs(); // Ensure target directory exists
//
//            try (FileWriter writer = new FileWriter(file)) {
//                writer.write(yamlSpec);
//            }
//
//            System.out.println("✅ OpenAPI YAML file saved to: " + OUTPUT_PATH);
//
//        } catch (IOException | InterruptedException e) {
//            System.err.println("❌ Failed to save OpenAPI spec: " + e.getMessage());
//        }
    }
}
