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
 * @author Admin
 */
public class CategoryProducts {
    static public List<Product> getProductByCategory(String cid) {
        List<Product> list = new ArrayList<Product>();

        String query = "SELECT id, name, quantity, price, category, thumbnail, description, unit, creatorId FROM Products\n"
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
                    rs.getInt("creatorId"))
                );
            }
            
        } catch (Exception e) {}

        return list;
    }
    
    static public List<CategoryProductStats> getCategoryProductStats(int userId) {
        List<CategoryProductStats> list = new ArrayList<CategoryProductStats>();
    
        String query = "SELECT ProductCategories.name AS name, SUM(OrderProduct.quantity) AS totalItems " +
                        "FROM Orders " +
                        "JOIN OrderProduct ON Orders.id = OrderProduct.orderId " +
                        "JOIN Products ON Products.id = OrderProduct.productId " +
                        "JOIN ProductCategories ON ProductCategories.id = Products.category " +
                        "JOIN Users ON Products.creatorId = Users.id " +
                        "WHERE Orders.status = 2 AND Users.id = ? " +
                        "GROUP BY ProductCategories.id, ProductCategories.name;";

        try {
            Connection conn = Model.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            
            ps.setInt(1, userId);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new CategoryProductStats(rs.getString("name"), rs.getInt("totalItems")));
            }
        } catch (Exception e) {}
        
        return list;
    }
    
    public static void main(String[] args) {
        List<CategoryProductStats> list = CategoryProducts.getCategoryProductStats(1);
        
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getName() + ": " + list.get(i).getTotalItems());
        }
    }
}
