package com.company.model;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.DriverManager;


public class MySQLConnector {
    public static BasicDataSource dataSource = new BasicDataSource();
    public static MySQLConnector dbManager;

    public static Connection getConnection() throws SQLException, IOException, ClassNotFoundException {
        Properties appProps = new Properties();
        appProps.load(MySQLConnector.class.getResourceAsStream("/application.properties"));

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(appProps.getProperty("url"));
    }
}
