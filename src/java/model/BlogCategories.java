/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class BlogCategories {
    static public List<BlogCategory> all(){
       List<BlogCategory> list = new ArrayList<BlogCategory>();
    
        String query = "SELECT * FROM BlogCategories";

        try {
            Connection conn = Model.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new BlogCategory(rs.getInt("id"), rs.getString("name")));
            }
        } catch (Exception e) {}
        
        return list;
    }
    
    public static void main(String[] args) {
        List<BlogCategory> list = BlogCategories.all();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getName());
        }
    }
    
}

