package dev.service.cloud.global;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Slf4j
public class DBUtil {

    public static Connection getConnection() {

        try {
            Properties prop = readProperties();
            final String USER_NAME = prop.getProperty("username");
            final String PASSWORD = prop.getProperty("password");
            final String DB_URL = prop.getProperty("url");
            final String DATABASE = prop.getProperty("database");

            log.info("{} {} {} {}", USER_NAME, PASSWORD, DB_URL, DATABASE);

            return DriverManager.getConnection(DB_URL + DATABASE, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Properties readProperties() {
        Properties properties = new Properties();

        try (InputStream input = DBUtil.class.getClassLoader().getResourceAsStream("application.yml")) {
            properties.load(input);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        return properties;
    }
}

