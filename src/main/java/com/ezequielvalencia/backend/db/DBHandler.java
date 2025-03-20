package com.ezequielvalencia.backend.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class DBHandler {
    private final static Logger logger = LogManager.getLogger();
    private final static String createGeoCacheTable = "CREATE TABLE IF NOT EXISTS geo_cache(" +
            "id INTEGER NOT NULL PRIMARY KEY" +
            "username TEXT NOT NULL" +
            "note TEXT NOT NULL" +
            "secret TEXT" +
            "date TIMESTAMP NOT NULL" +
            ")";

    private final static String[] tablesToCreate = {createGeoCacheTable};

    private final JdbcTemplate template;

    public DBHandler(JdbcTemplate dataSource){
        template = dataSource;
    }

    public void createTables() throws URISyntaxException, IOException {
        URL sqlURL = DBHandler.class.getClassLoader().getResource("tables.sql");
        assert sqlURL != null;
        String sql = Files.readString(Path.of(sqlURL.toURI()));
        template.execute(sql);
        logger.info("Created Tables");
    }


//    @Value("${spring.datasource.url}")
//    private static String dbURL;
//    @Value("${spring.datasource.password}")
//    private static String dbPassword;
//    @Value("${spring.datasource.username}")
//    private static String dbUsername;

//    @Bean
//    public static DataSource createDataSource(){
//        String propertiesPath = Objects.requireNonNull(DBHandler.class.getClassLoader().getResource("hikari.properties")).getFile();
//        System.out.println(propertiesPath);
//        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setConnectionTimeout(20000);
//        hikariConfig.setMaxLifetime(1800000);
//        hikariConfig.setPoolName("MainPool");
//        hikariConfig.setIdleTimeout(30000);
//        hikariConfig.setMaximumPoolSize(20);
//        hikariConfig.setUsername(dbUsername);
//        hikariConfig.setPassword(dbPassword);
//        hikariConfig.setJdbcUrl(dbURL);
//        System.out.println(hikariConfig.getJdbcUrl());
//        return new HikariDataSource(hikariConfig);
//    }

//    private Properties hibernateProperties() {
//        Properties properties = new Properties();
//        properties.setProperty("hibernate.dialect", "postgres");
//        logger.info("Hibernate properties created");
//        return properties;
//    }
//
//    @Bean
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(createDataSource());
//        sessionFactory.setPackagesToScan("com.spring.boot.learning"); // Replace with your package name
//        sessionFactory.setHibernateProperties(hibernateProperties());
//        logger.info("session factory created");
//        return sessionFactory;
//    }
//
//    @Bean
//    public PlatformTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) {
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//        transactionManager.setSessionFactory(sessionFactory);
//        return transactionManager;
//    }


//    public DBHandler(){
//        createDataSource();
//    }


//    public LocalSessionFactoryBean sessionFactory(){
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//
//        sessionFactory.setDataSource(createDataSource());
//        sessionFactory.setPackagesToScan("com.spring.boot.learning"); // Replace with your package name
//        sessionFactory.setHibernateProperties(hibernateProperties());
//        logger.info("session factory created");
//        return sessionFactory;
//    }

//    protected void initializeDB(){
//
//        try (Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
//
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//            pstmt.setString(1, text);
//            int rowsInserted = pstmt.executeUpdate();
//
//            if (rowsInserted > 0) {
//                System.out.println("Text inserted successfully!");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
