/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ProductCategories {

    static public List<Product> getProductByCategory(String cid) throws SQLException {
        List<Product> list = new ArrayList<Product>();

        String query = "SELECT id, name, quantity, price, category, thumbnail, description, unit, creatorId FROM Products\n"
                + "WHERE category = ?";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = Model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, cid);
            rs = ps.executeQuery();
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

    static public List<ProductCategoryStats> getCategoryProductStats(int userId) throws SQLException {
        List<ProductCategoryStats> list = new ArrayList<ProductCategoryStats>();

        String query = "SELECT ProductCategories.name AS name, SUM(OrderProduct.quantity) AS totalItems "
                + "FROM Orders "
                + "JOIN OrderProduct ON Orders.id = OrderProduct.orderId "
                + "JOIN Products ON Products.id = OrderProduct.productId "
                + "JOIN ProductCategories ON ProductCategories.id = Products.category "
                + "JOIN Users ON Products.creatorId = Users.id "
                + "WHERE Orders.status = 2 AND Users.id = ? "
                + "GROUP BY ProductCategories.id, ProductCategories.name;";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = Model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ProductCategoryStats(rs.getString("name"), rs.getInt("totalItems")));
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

    static public List<ProductCategory> allCategory() throws SQLException {
        List<ProductCategory> list = new ArrayList<ProductCategory>();

        String query = "SELECT * FROM ProductCategories";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = Model.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ProductCategory(rs.getInt("id"), rs.getString("name")));
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
}
