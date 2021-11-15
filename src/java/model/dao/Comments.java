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
import model.Comment;
import model.IModel;
import model.Model;

/**
 *
 * @author LAPTOP D&N
 */
public class Comments {
    static private IModel model = new Model();
    
    static public void setModel(IModel alternativeModel) {
        model = alternativeModel;
    }
    
    static public List<Comment> getAllComment() throws SQLException {
        List<Comment> list = new ArrayList<>();
        String query = "SELECT * FROM BlogComments \n"
                + "JOIN Users ON Users.id = BlogComments.authorId";
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
            
        try {
            conn = model.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                list.add(new Comment(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getDate(4),
                        rs.getString(5),
                        rs.getString(6)));
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
        }
        
        return list;
    }

    static public List<Comment> getCommentByBlogID(int blogid) throws SQLException {
        List<Comment> list = new ArrayList<>();
        String query = "select * from BlogComments \n"
                + "JOIN Users ON Users.id = BlogComments.authorId \n"
                + "where blogid= ?";
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        
        try {
            conn = model.getConnection();
            
            ps = conn.prepareStatement(query);
            ps.setInt(1, blogid);
            
            rs = ps.executeQuery();
            
            while (rs.next()) {
                list.add(new Comment(rs.getInt("id"),
                        rs.getInt("authorId"),
                        rs.getInt("blogId"),
                        rs.getDate("createdAt"),
                        rs.getString("content"),
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

    static public Comment insertComment(int authorId, int blogId, Date createdAt, String content) throws SQLException {

        PreparedStatement ps = null;

        Connection conn = null;

        try {

            String query = "INSERT INTO BlogComments\n" 
                    + "(authorId, blogId, createdAt, content)\n" 
                    + "VALUES (?,?,?,?)";

            conn = model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, authorId);
            ps.setInt(2, blogId);
            ps.setDate(3, createdAt);
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

    public static void main(String[] args) throws SQLException {
        
        long millis = System.currentTimeMillis();
        java.sql.Date createdAt = new java.sql.Date(millis);

        Comments.insertComment(1, 1, createdAt, "a");
    }
}
