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
public class Products extends Model {
    public static List<Product> getPremiumProduct() {
        List<Product> list = new ArrayList<Product>();
        String query = "select top 4 * from Products\n"
                + "order by price desc, name asc";

        try {
            Connection conn = Model.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
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
        } catch (Exception e) {
        }

        return list;
    }
    
    static public List<Product> searchByName(String txtSearch) {
        List<Product> list = new ArrayList<Product>();

        String query = "select * from Products\n"
                + "where [name] like ?";

        try {
            Connection conn = Model.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%");
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
        } catch (Exception e) {
        }

        return list;
    }
    
    static public List<ProductStats> getProductStats(int userId) {
        List<ProductStats> list = new ArrayList<ProductStats>();
    
        String query = "SELECT Products.id AS id, Products.name AS name, Products.price AS price, SUM(OrderProduct.quantity) AS sold, Products.quantity AS remained, SUM(OrderProduct.quantity * Products.price) AS total " +
                        "FROM Orders " +
                        "JOIN OrderProduct ON Orders.id = OrderProduct.orderId " +
                        "JOIN Products ON Products.id = OrderProduct.productId " +
                        "JOIN Users ON Products.creatorId = Users.id " +
                        "WHERE Orders.status = 2 AND Users.id = ? " +
                        "GROUP BY Products.id, Products.name, Products.price, Products.quantity " +
                        "ORDER BY sold DESC;";

        try {
            Connection conn = Model.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            
            ps.setInt(1, userId);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ProductStats(
                        rs.getInt("id"), 
                        rs.getString("name"), 
                        rs.getDouble("price"), 
                        rs.getInt("sold"), 
                        rs.getInt("remained"), 
                        rs.getDouble("total")
                ));
            }
        } catch (Exception e) {}
        
        return list;
    }
    
    static public Product getProductByID(String id){
        String query = "select * from Products\n"
                        + "where id=?";
        try {
            Connection conn = Model.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(
                            rs.getInt("id"), 
                            rs.getString("name"), 
                            rs.getInt("quantity"), 
                            rs.getDouble("price"), 
                            rs.getInt("category"), 
                            rs.getString("thumbnail"), 
                            rs.getString("description"), 
                            rs.getString("unit"), 
                            rs.getInt("creatorId")
                );
            }
        } catch (Exception e) {}
        
        return null;
    }
    
    public static void main(String[] args) {
        List<ProductStats> list = Products.getProductStats(1);
        
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getName());
        }
        
//        List<Product> list1 = Products.getPremiumProduct();
//        for (int i = 0; i < list.size(); i++)  {
//            System.out.println(list.get(i).getName());
//        }
    }
}
