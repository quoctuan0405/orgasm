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
public class Model implements IModel {
    public Connection getConnection() throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String url = "jdbc:sqlserver://" + ModelConfig.serverName + ":" + ModelConfig.portNumber + ";databaseName=" + ModelConfig.dbName;
            Connection conn = DriverManager.getConnection(url, ModelConfig.user, ModelConfig.password);
            
            return conn;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
