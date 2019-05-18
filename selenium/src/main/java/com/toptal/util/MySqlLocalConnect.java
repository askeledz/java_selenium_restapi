package com.toptal.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

/**
 * @author askeledzija
 */
// Sequel Pro - for Database
// Driver Download - https://dev.mysql.com/downloads/connector/odbc/
public class MySqlLocalConnect {

    private static final Logger logger = LogManager.getLogger(MySqlLocalConnect.class);
    // Connection object
    private static Connection conn = null;
    // Statement object
    private static Statement stmt = null;
    // Result Set
    private static ResultSet results = null;

    public static Statement getStmt() {
        return stmt;
    }

    public Connection openDB() {
        try {
            // STEP 1: Register JDBC driver
            Class.forName(Config.MYSQL_DRIVER).newInstance();

            // STEP 2: Get connection to DB
            logger.info("Connecting to a selected database...");
            conn = DriverManager.getConnection(Config.MYSQL_DBURL, Config.MYSQL_USER, Config.MYSQL_PASSWORD);
            logger.info("Connected database successfully...");

            // STEP 3: Statement object to send the SQL statement to the Database
            logger.info("Creating statement...");
            stmt = conn.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeDb() {
        try {
            if (results != null)
                results.close();
            logger.info("results.close();");
            if (stmt != null)
                conn.close();
            logger.info("stmt - conn.close();");
            if (conn != null)
                conn.close();
            logger.info("conn.close();");
        } catch (SQLException se) {
            se.printStackTrace();
        }

    }
}
