/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author Admin
 */
public class Model implements IModel {
    public Connection getConnection() throws Exception {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/orgasm","root","");
            
            return connection;
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return null;
    }
    
    public static void main(String[] args) throws Exception {
        Model model = new Model();
        model.getConnection();
    }
}
