/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.IModel;
import model.Model;
import model.entity.ProductFeedback;
import model.entity.Product;

/**
 *
 * @author Administrator
 */
public class ProductFeedbacks {
    static private IModel model = new Model();

    public static Product getProductByOrderProductId(int OrderProductId) {

        String query = "select Products.*\n"
                + "from Products\n"
                + "join OrderProduct on Products.id = OrderProduct.productId\n"
                + "where OrderProduct.id=?";
        try {
            Connection conn = model.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, OrderProductId);

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
        } catch (Exception e) {
        }
        return null;
    }

    public static int getRating(int id) {
        String query = "select AVG(ProductFeedbacks.rating)\n"
                + "from ProductFeedbacks\n"
                + "join OrderProduct on ProductFeedbacks.orderProductId = OrderProduct.id\n"
                + "where OrderProduct.productId=?";
        try {
            Connection conn = model.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }

        return 0;
    }

    public static void addFeedback(int orderProductId, int rating, String description) throws SQLException {
        LocalDate curDate = java.time.LocalDate.now();
        String date = curDate.toString();
        String query = "INSERT INTO ProductFeedbacks (orderProductId, createdAt, rating, description) VALUES (?,?,?,?)";
        
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = model.getConnection();
            ps = conn.prepareStatement(query);
            
            ps.setInt(1, orderProductId);
            ps.setString(2, date);
            ps.setInt(3, rating);
            ps.setString(4, description);
            
            ps.executeUpdate();
            
        } catch (Exception e) {
            e.getMessage();
            
        } finally {
            if (conn != null) {
                conn.close();
            }
            
            if (ps != null) {
                conn.close();
            }
        }

    }
    
    static public List<ProductFeedback> getProductFeedbacks(int cid) throws SQLException {
        List<ProductFeedback> list = new ArrayList<ProductFeedback>();
        String query = "select ProductFeedbacks.*, Users.avatar\n"
                + "from ProductFeedbacks\n"
                + "join OrderProduct on ProductFeedbacks.orderProductId = OrderProduct.id\n"
                + "join Orders on OrderProduct.orderId=Orders.id\n"
                + "join Users on Users.id= Orders.buyerId\n"
                + "where OrderProduct.productId = ?";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, cid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ProductFeedback(rs.getInt("id"),
                        rs.getInt("orderProductId"),
                        rs.getString("createdAt"),
                        rs.getInt("rating"),
                        rs.getString("description"),
                        rs.getString("avatar")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return list;
    }

    static public double AVGFeedback(int id) throws SQLException {
        String query = "select AVG(ProductFeedbacks.rating)\n"
                + "from ProductFeedbacks\n"
                + "join OrderProduct on ProductFeedbacks.orderProductId = OrderProduct.id\n"
                + "where OrderProduct.productId=?";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return 0;
    }
    
    public static void main(String[] args) throws SQLException {
        ProductFeedbacks.addFeedback(1, 5, "Great!!!");
    }

}
