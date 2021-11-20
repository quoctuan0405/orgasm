/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.IModel;
import model.Model;
import model.entity.Ticket;
import model.entity.TicketComment;

/**
 *
 * @author LAPTOP D&N
 */
public class Tickets {
    static private IModel model = new Model();
    
    static public void setModel(IModel alternativeModel) {
        model = alternativeModel;
    }
    
    static public List<TicketComment> getCommentByTicketId(String ticketId) throws SQLException {
        List<TicketComment> list = new ArrayList<>();
        String query = "select * from TicketComments \n"
                + "JOIN Users ON Users.id = TicketComments.authorId \n"
                + "where ticketId = ?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, ticketId);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new TicketComment(rs.getInt("id"),
                        rs.getInt("authorId"),
                        rs.getDate("createdAt"),
                        rs.getInt("ticketId"),
                        rs.getString("content"),
                        rs.getString("avatar")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return list;
    }

    static public TicketComment insertComment(int authorId, Date createdAt, int ticketId, String content) throws SQLException {

        PreparedStatement ps = null;
        Connection conn = null;

        try {

            String query = "INSERT INTO TicketComments\n"
                    + "(authorId, createdAt, ticketId, content)\n"
                    + "Values (?, ?, ?, ?)";

            conn = model.getConnection();
            ps = conn.prepareStatement(query);

            ps.setInt(1, authorId);
            ps.setDate(2, createdAt);
            ps.setInt(3, ticketId);
            ps.setString(4, content);

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
        return null;
    }

    static public List<Ticket> getAllTicket() throws SQLException {
        List<Ticket> list = new ArrayList<Ticket>();
        String query = "select * from Tickets\n"
                + "join Users on Users.id = Tickets.authorId";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = model.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Ticket(rs.getInt("id"),
                        rs.getInt("authorId"),
                        rs.getDate("createdAt"),
                        rs.getString("content"),
                        rs.getString("title"),
                        rs.getString("avatar"),
                        rs.getString("username")));
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

    static public List<Ticket> getTicketByAuthorId(int authorId) throws SQLException {
        List<Ticket> list = new ArrayList<Ticket>();
        String query = "select * from Tickets\n"
                + "JOIN Users ON Users.id = Tickets.authorId\n"
                + "where authorId = ?";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, authorId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Ticket(rs.getInt("id"),
                        rs.getInt("authorId"),
                        rs.getDate("createdAt"),
                        rs.getString("content"),
                        rs.getString("title"),
                        rs.getString("avatar"),
                        rs.getString("username")));
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

    static public Ticket getTicketByID(String id) throws SQLException {
        String query = "select * from Tickets\n"
                + "join Users on Users.id = Tickets.authorId\n"
                + "where Tickets.id = ?";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Ticket(rs.getInt("id"),
                        rs.getInt("authorId"),
                        rs.getDate("createdAt"),
                        rs.getString("content"),
                        rs.getString("title"),
                        rs.getString("avatar"),
                        rs.getString("username"));
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

    static public Ticket addTicket(int authorId, Date createdAt, String content, String title) throws SQLException {
        PreparedStatement ps = null;
        Connection conn = null;

        try {

            String query = "INSERT INTO Tickets\n"
                    + "(authorId, createdAt, content, title)\n"
                    + "Values (?, ?, ?, ?)";

            conn = model.getConnection();
            ps = conn.prepareStatement(query);

            ps.setInt(1, authorId);
            ps.setDate(2, createdAt);
            ps.setString(3, content);
            ps.setString(4, title);

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
        return null;
    }

    static public void RemoveTicket(String id) throws SQLException {
        String query = "delete from TicketComments where ticketId = ? ";

        String query2 = "delete from Tickets where id = ?";
        ;

        PreparedStatement TicketCommentsPs = null;
        PreparedStatement TicketsPs = null;
        Connection conn = null;

        try {
            conn = model.getConnection();

            TicketCommentsPs = conn.prepareStatement(query);
            TicketsPs = conn.prepareStatement(query2);

            TicketCommentsPs.setString(1, id);
            TicketsPs.setString(1, id);

            TicketCommentsPs.executeUpdate();
            TicketsPs.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (TicketCommentsPs != null) {
                TicketCommentsPs.close();
            }
            if (TicketsPs != null) {
                TicketsPs.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    static public int totalTicketbyAuthor(int userId) throws SQLException {
        String query = "select COUNT(*) from Tickets\n"
                + "join Users on Users.id = Tickets.authorId\n"
                + "where authorId = ?";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
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

    static public int totalTicket() throws SQLException {
        String query = "select COUNT(*) from Tickets";

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

    static public List<Ticket> paging(int userId, int page) throws SQLException {
        List<Ticket> list = new ArrayList<>();
        String query = "select * from Tickets \n"
                + "join Users on Users.id = Tickets.authorId\n"
                + "where authorId = ? \n"
                + "Order by Tickets.id \n"
                + "limit ?, 5";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, (page - 1) * 5);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Ticket(rs.getInt("id"),
                        rs.getInt("authorId"),
                        rs.getDate("createdAt"),
                        rs.getString("content"),
                        rs.getString("title"),
                        rs.getString("avatar"),
                        rs.getString("username")));
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

    static public List<Ticket> totalpaging(int page) throws SQLException {
        List<Ticket> list = new ArrayList<>();
        String query = "select * from Tickets\n"
                + "join Users on Users.id = Tickets.authorId\n"
                + "order by Tickets.id \n"
                + "limit ?, 5";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, (page - 1) * 5);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Ticket(rs.getInt("id"),
                        rs.getInt("authorId"),
                        rs.getDate("createdAt"),
                        rs.getString("content"),
                        rs.getString("title"),
                        rs.getString("avatar"),
                        rs.getString("username")));
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

    static public List<Ticket> searchTickets(String txtSearch) throws SQLException {
        List<Ticket> list = new ArrayList<Ticket>();

        String query = "select * from Tickets\n"
                + "join Users on Users.id = Tickets.authorId\n"
                + "where Tickets.title like ?\n"
                + "or Users.username like ?";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%");
            ps.setString(2, "%" + txtSearch + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Ticket(rs.getInt("id"),
                        rs.getInt("authorId"),
                        rs.getDate("createdAt"),
                        rs.getString("content"),
                        rs.getString("title"),
                        rs.getString("avatar"),
                        rs.getString("username")));
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

    static public List<Ticket> UserTicketSearch(String txtSearch, int userId) throws SQLException {
        List<Ticket> list = new ArrayList<Ticket>();

        String query = "select * from Tickets\n"
                + "join Users on Users.id = Tickets.authorId\n"
                + "where Tickets.authorId = ? \n"
                + "and Tickets.title like ? \n"
                + "or Users.username like ? \n";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setString(2, "%" + txtSearch + "%");
            ps.setString(3, "%" + txtSearch + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Ticket(rs.getInt("id"),
                        rs.getInt("authorId"),
                        rs.getDate("createdAt"),
                        rs.getString("content"),
                        rs.getString("title"),
                        rs.getString("avatar"),
                        rs.getString("username")));
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

        List<Ticket> ticket = searchTickets("username");
        for (Ticket ticket1 : ticket) {
            System.out.println(ticket1);
        }
        
        Tickets.addTicket(1, new Date(2021, 01, 01), "content", "title");

    }

}
