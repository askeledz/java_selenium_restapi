package com.toptal.util;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
/**
 * @author askeledzija
 */
public class MySqlRemoteConnect {

    private static final Logger logger = LogManager.getLogger(MySqlRemoteConnect.class);
    private static Statement stmt = null;
    private static Connection conn = null;
    private static ResultSet results = null;

    public static Statement getStmt() {
        return stmt;
    }

    public static void portForwarding() {

        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(Config.SSH_USER, Config.SSH_HOST, 22);
            session.setPassword(Config.SSH_PASSWORD);
            session.setConfig("StrictHostKeyChecking", "no");
            logger.info("Establishing Connection...");
            session.connect();
            int assinged_port = session.setPortForwardingL(1234, "localhost", 3306);
            logger.info("localhost:" + assinged_port + " -> " + 1234 + ":" + 3306);
        } catch (Exception e) {
            System.err.print(e);
        }
    }

    public Connection connectDB() {
        try {

            portForwarding();

            logger.info("Retrieving data from Mysql Database ....");

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