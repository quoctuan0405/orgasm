/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Admin
 */
public class Model {
    private static final String serverName = "ADMIN";
    private static final String dbName = "orgasm";
    private static final String portNumber = "1433";
    private static final String user = "sa";
    private static final String password = "root";
    
    public static Connection getConnection() throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
            Connection conn = DriverManager.getConnection(url, user, password);
            
            return conn;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
