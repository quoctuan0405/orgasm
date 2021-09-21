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
public class ProductCategories {
    static public List<ProductCategory> allCategory() {
        List<ProductCategory> list = new ArrayList<ProductCategory>();

        String query = "SELECT * FROM ProductCategories";

        try {
            Connection conn = Model.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ProductCategory(rs.getInt("id"), rs.getString("name")));
            }
        } catch (Exception e) {
        }

        return list;
    }
     
    static public List<Product> getProductByCategory(String cid) {
        List<Product> list = new ArrayList<Product>();

        String query = "SELECT * FROM Products "
                        + "WHERE category = ?";

        try {
            Connection conn = Model.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, cid);
            ResultSet rs = ps.executeQuery();           
            while (rs.next()) {
                list.add(new Product(
                    rs.getInt("id"), 
                    rs.getString("name"), 
                    rs.getInt("quantity"), 
                    rs.getDouble("price"), 
                    rs.getInt("category"), 
                    rs.getString("thumbnail"), 
                    rs.getString("description"), 
                    rs.getString("unit"), 
                    rs.getInt("creatorId")
                ));
            }
        } catch (Exception e) {}

        return list;
    }
}
