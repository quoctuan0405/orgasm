/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.entity.Product;
import model.entity.ProductStats;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.IModel;
import model.Model;

/**
 *
 * @author Admin
 */
public class Products {
    static private IModel model = new Model();
    
    static public void setModel(IModel alternativeModel) {
        model = alternativeModel;
    }

    public static List<Product> getPremiumProduct() throws SQLException {
        List<Product> list = new ArrayList<Product>();
        String query = "select * from Products\n"
                + "order by price desc, name asc "
                + "limit 4";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = model.getConnection();
            ps = conn.prepareStatement(query);
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
                        rs.getInt("creatorId")
                ));
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

    static public List<Product> searchByName(String txtSearch) throws SQLException {
        List<Product> list = new ArrayList<Product>();

        String query = "select * from Products\n"
                + "where name like ?";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%");
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
                        rs.getInt("creatorId")
                ));
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

    static public List<ProductStats> getProductStats(int userId) throws SQLException {
        List<ProductStats> list = new ArrayList<ProductStats>();

        String query = "SELECT Products.id AS id, Products.name AS name, Products.price AS price, SUM(OrderProduct.quantity) AS sold, Products.quantity AS remained, SUM(OrderProduct.quantity * Products.price) AS total "
                + "FROM Orders "
                + "JOIN OrderProduct ON Orders.id = OrderProduct.orderId "
                + "JOIN Products ON Products.id = OrderProduct.productId "
                + "JOIN Users ON Products.creatorId = Users.id "
                + "WHERE Orders.status = 2 AND Users.id = ? "
                + "GROUP BY Products.id, Products.name, Products.price, Products.quantity "
                + "ORDER BY total DESC;";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = model.getConnection();
            ps = conn.prepareStatement(query);

            ps.setInt(1, userId);

            rs = ps.executeQuery();
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
    
    static public List<ProductStats> getProductStatsAdmin() throws SQLException {
        List<ProductStats> list = new ArrayList<ProductStats>();

        String query = "SELECT Products.id AS id, Products.name AS name, Products.price AS price, SUM(OrderProduct.quantity) AS sold, Products.quantity AS remained, SUM(OrderProduct.quantity * Products.price) AS total "
                + "FROM Orders "
                + "JOIN OrderProduct ON Orders.id = OrderProduct.orderId "
                + "JOIN Products ON Products.id = OrderProduct.productId "
                + "JOIN Users ON Products.creatorId = Users.id "
                + "WHERE Orders.status = 2 "
                + "GROUP BY Products.id, Products.name, Products.price, Products.quantity "
                + "ORDER BY total DESC;";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = model.getConnection();
            ps = conn.prepareStatement(query);

            rs = ps.executeQuery();
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

    static public Product getProductByID(int id) throws SQLException {
        String query = "select * from Products\n"
                + "where id=?";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
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

        return null;
    }

    static public int total() throws SQLException {
        String query = "select COUNT(*) from Products ";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = model.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
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

    static public List<Product> paging(int page) throws SQLException {
        List<Product> list = new ArrayList<>();
        String query = "select * from Products\n"
                + "Order by id \n"
                + "LIMIT ?, 4";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, (page - 1) * 4);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getInt("category"),
                        rs.getString("thumbnail"),
                        rs.getString("description"),
                        rs.getString("unit"),
                        rs.getInt("creatorId")));
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

    public static void deleteProduct(int deleteID) throws SQLException {
        String deleteAssociatedFeedbackQuery = "delete from ProductFeedbacks " +
                                                "where orderProductId in " +
                                                "(select id from OrderProduct where productId = ?)";
        
        String deleteAssociatedOrderProductQuery = "delete from OrderProduct "
                + "where productId = ?";

        String deleteProductQuery = "delete from Products "
                + "where id = ?";

        PreparedStatement orderProductPS = null;
        PreparedStatement feedbackPS = null;
        PreparedStatement productPS = null;
        Connection conn = null;

        try {
            conn = model.getConnection();

            feedbackPS = conn.prepareStatement(deleteAssociatedFeedbackQuery);
            orderProductPS = conn.prepareStatement(deleteAssociatedOrderProductQuery);
            productPS = conn.prepareStatement(deleteProductQuery);

            feedbackPS.setInt(1, deleteID);
            orderProductPS.setInt(1, deleteID);
            productPS.setInt(1, deleteID);

            feedbackPS.executeUpdate();
            orderProductPS.executeUpdate();
            productPS.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (orderProductPS != null) {
                orderProductPS.close();
            }
            if (feedbackPS != null) {
                feedbackPS.close();
            }
            if (productPS != null) {
                productPS.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public static void addProduct(String name, String price, String quantity, String category, String thumbnail, String description) throws SQLException {
        String query = "INSERT INTO Products (name, price, quantity, category, thumbnail, description, creatorId)\n"
                + "VALUES (?, ?, ?, ?, ?, ?, 1)";

        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, price);
            ps.setString(3, quantity);
            ps.setString(4, category);
            ps.setString(5, thumbnail);
            ps.setString(6, description);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

    }

    public static void editProduct(String name, String quantity, String price, String category, String thumbnail, String description, String id) throws SQLException {
        String query = "update Products\n"
                + "set name = ?, quantity = ?, price = ?,\n"
                + "category = ?, thumbnail = ?, description = ?\n"
                + "where id = ?";

        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, quantity);
            ps.setString(3, price);
            ps.setString(4, category);
            ps.setString(5, thumbnail);
            ps.setString(6, description);
            ps.setString(7, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public static List<Product> getProductByCreatorID(int creatorId) throws SQLException {
        List<Product> list = new ArrayList<Product>();
        String query = "select * from Products "
                        + "where creatorId = ?";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, creatorId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getInt("category"),
                        rs.getString("thumbnail"),
                        rs.getString("description"),
                        rs.getString("unit"),
                        rs.getInt("creatorId")));
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
    
    static public List<Product> getProductByUser(int id) throws SQLException {
        
        List<Product> list = new ArrayList<>();
        
        String query = "select * from Products\n"
                + "JOIN Users ON Users.id = Products.creatorId\n"
                + "WHERE creatorId=?";
        
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        
        try {

            conn = model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Product(rs.getInt("id"), 
                            rs.getString("name"), 
                            rs.getInt("quantity"), 
                            rs.getDouble("price"), 
                            rs.getInt("category"), 
                            rs.getString("thumbnail"), 
                            rs.getString("description"), 
                            rs.getString("unit"), 
                            rs.getInt("creatorId")));
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

    public static void main(String[] args) throws SQLException {
//        List<ProductStats> list = Products.getProductStats(1);
//        
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i).getName());
//        }

//        List<Product> list1 = Products.getPremiumProduct();
//        for (int i = 0; i < list.size(); i++)  {
//            System.out.println(list.get(i).getName());
//        }
        List<Product> list = Products.getPremiumProduct();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getName());
        }
    }
}
