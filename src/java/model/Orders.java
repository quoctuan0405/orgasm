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
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class Orders extends Model {

    static public List<OrderStats> revenueByMonth(int userId) throws SQLException {
        List<OrderStats> list = new ArrayList<>();

        String query = "SELECT TOP(5) Orders.updatedAt AS date, SUM(OrderProduct.quantity * Products.price) AS total "
                + "FROM Orders "
                + "JOIN OrderProduct ON Orders.id = OrderProduct.orderId "
                + "JOIN Products ON Products.id = OrderProduct.productId "
                + "JOIN Users ON Products.creatorId = Users.id "
                + "WHERE Orders.status = 2 AND Users.id = ? "
                + "GROUP BY MONTH(Orders.updatedAt), Orders.updatedAt "
                + "ORDER BY MONTH(Orders.updatedAt) DESC";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = Model.getConnection();
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

}
