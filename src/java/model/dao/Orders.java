/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.entity.OrderStats;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.IModel;
import model.Model;
import model.entity.ProductInCart;
import model.entity.Order;
import model.entity.OrderProduct;
import model.entity.Product;

/**
 *
 * @author Admin
 */
public class Orders extends Model {
    static private IModel model = new Model();
    
    static public void setModel(IModel alternativeModel) {
        model = alternativeModel;
    }
    
    static public List<OrderStats> revenueByMonth(int userId) throws SQLException {
        List<OrderStats> list = new ArrayList<>();

        String query = "SELECT Orders.updatedAt AS date, SUM(OrderProduct.quantity * Products.price) AS total "
                + "FROM Orders "
                + "JOIN OrderProduct ON Orders.id = OrderProduct.orderId "
                + "JOIN Products ON Products.id = OrderProduct.productId "
                + "JOIN Users ON Products.creatorId = Users.id "
                + "WHERE Orders.status = 2 AND Users.id = ? "
                + "GROUP BY MONTH(Orders.updatedAt), Orders.updatedAt "
                + "ORDER BY MONTH(Orders.updatedAt) DESC "
                + "LIMIT 5";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new OrderStats(rs.getDate("date"), rs.getDouble("total")));
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
    
    public void addOrder(int id, Cart carts) throws SQLException {
        LocalDate curDate = java.time.LocalDate.now();
        String date = curDate.toString();
        
        Connection con = null;
        PreparedStatement st = null;
        PreparedStatement st1 = null;
        PreparedStatement st2 = null;
        PreparedStatement st3 = null;
        ResultSet rs = null;
        
        try {
            con = model.getConnection();
            //add vào bảng Order
            String sql = "INSERT INTO Orders (buyerId, createdAt, updatedAt, status) VALUES(?,?,?,?)";
            st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.setString(2, date);
            st.setString(3, date);
            st.setInt(4, 1);
            st.executeUpdate();

            //lấy ra id của Order vừa add
            String sql1 = "select id from Orders order by id desc LIMIT 1";
            st1 = con.prepareStatement(sql1);
            rs = st1.executeQuery();

            //add vào bảng OrderProduct
            if (rs.next()) {
                int oid = rs.getInt(1);
                for (ProductInCart i : carts.getProducts()) {
                    String sql2 = "INSERT INTO OrderProduct (orderId, productId, quantity) VALUES(?,?,?)";
                    st2 = con.prepareStatement(sql2);
                    st2.setInt(1, oid);
                    st2.setInt(2, i.getProduct().getId());
                    st2.setInt(3, i.getQuantity());
                    st2.executeUpdate();
                }
            }
            //update so luong trong bang Product sau khi ban
            String sql3 = "update Products set quantity = quantity - ? "
                    + "where id = ?";
            st3 = con.prepareStatement(sql3);
            for (ProductInCart i : carts.getProducts()) {
                st3.setInt(1, i.getQuantity());
                st3.setInt(2, i.getProduct().getId());
                st3.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println(e);
            
        } catch (Exception e) {
            System.out.println(e);
            
        } finally {
            if (con != null) {
                con.close();
            }
            
            if (rs != null) {
                rs.close();
            }
            
            if (st != null) {
                st.close();
            }
            
            if (st1 != null) {
                st1.close();
            }
            
            if (st2 != null) {
                st2.close();
            }
            
            if (st3 != null) {
                st3.close();
            }
        }
    }

    static public List<Order> getOrderByUserid(int userId) throws SQLException {
        List<Order> list = new ArrayList<>();
        String query = "select * from Orders\n"
                + "where buyerId = ? "
                + "order by id desc";
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = model.getConnection();
            ps = conn.prepareStatement(query);

            ps.setInt(1, userId);

            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Order(
                        rs.getInt("id"),
                        rs.getInt("buyerId"),
                        rs.getString("createdAt"),
                        rs.getString("updatedAt"),
                        rs.getInt("status"))
                );
            }
        } catch (Exception e) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, e);
            
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

    static public List<Product> getProductByBuyerId(int buyerId) throws SQLException {
        List<Product> list = new ArrayList<>();
        String query = "select Products.id, Products.name, Products.quantity, Products.price, Products.category, Products.thumbnail, Products.description, Products.unit, Orders.id as orderId\n"
                + "from Orders join OrderProduct on Orders.id = OrderProduct.orderId\n"
                + "join Products on OrderProduct.productId = Products.id\n"
                + "where Orders.buyerId = ?";
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
            
        try {
            conn = model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, buyerId);
            
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
                        rs.getInt("orderId")//khong dung creatorId nen luu tam orderId vao bien nay
                ));
            }
        } catch (Exception e) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, e);
            
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

    static public List<OrderProduct> getOrderProductByBuyerId(int buyerId) throws SQLException {
        List<OrderProduct> list = new ArrayList<>();
        String query = "select OrderProduct.*\n"
                + "from Orders join OrderProduct on Orders.id = OrderProduct.orderId\n"
                + "where Orders.buyerId = ?";
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, buyerId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new OrderProduct(rs.getInt("id"),
                        rs.getInt("orderId"),
                        rs.getInt("productId"),
                        rs.getInt("quantity"))
                );
            }
        } catch (Exception e) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, e);

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
        List<Order> orders = Orders.getOrderByUserid(1);
        
        for (int i = 0 ; i < orders.size(); i++) {
            System.out.println(orders.get(i).getBuyerId());
        }
    }

}
