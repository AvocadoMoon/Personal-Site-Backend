package com.ezequielvalencia.backend.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class DBHandler {
    private final static Logger logger = LogManager.getLogger();

    @Autowired
    private JdbcTemplate template;

    public DBHandler(JdbcTemplate dataSource){
        template = dataSource;
    }

    public DBHandler(){ }

    public void createTables() throws URISyntaxException, IOException {
        URL sqlURL = DBHandler.class.getClassLoader().getResource("tables.sql");
        assert sqlURL != null;
        String sql = Files.readString(Path.of(sqlURL.toURI()));
        template.execute(sql);
        logger.info("Created Tables");
    }

}
