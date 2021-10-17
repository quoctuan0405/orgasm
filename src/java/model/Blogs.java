/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.entity.Blog;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class Blogs {

    static public List<Blog> all() throws SQLException {
        List<Blog> list = new ArrayList<Blog>();

        String query = "SELECT * FROM Blogs";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = Model.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Blog(rs.getInt("id"),
                        rs.getString("image"),
                        rs.getString("title"),
                        rs.getDate("createdAt"),
                        rs.getString("content"),
                        rs.getInt("category"),
                        rs.getInt("authorid")));
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

    static public List<Blog> getBlogbyCategory(String id) throws SQLException {
        List<Blog> list = new ArrayList<>();
        String query = "SELECT * FROM Blogs WHERE category = ?";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = Model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Blog(rs.getInt("id"),
                        rs.getString("image"),
                        rs.getString("title"),
                        rs.getDate("createdAt"),
                        rs.getString("content"),
                        rs.getInt("category"),
                        rs.getInt("authorid")));
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

    static public List<Blog> search(String search) throws SQLException {
        List<Blog> list = new ArrayList<>();
        String query = "SELECT * FROM Blogs WHERE title LIKE ?";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = Model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + search + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Blog(rs.getInt("id"),
                        rs.getString("image"),
                        rs.getString("title"),
                        rs.getDate("createdAt"),
                        rs.getString("content"),
                        rs.getInt("category"),
                        rs.getInt("authorid")));
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

    static public int total() throws SQLException {
        String query = "SELECT COUNT(*) FROM Blogs";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = Model.getConnection();
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

    static public List<Blog> paging(int page) throws SQLException {
        List<Blog> list = new ArrayList<>();
        String query = "SELECT * FROM Blogs \n"
                + "ORDER BY id \n"
                + "OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = Model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, (page - 1) * 4);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Blog(rs.getInt("id"),
                        rs.getString("image"),
                        rs.getString("title"),
                        rs.getDate("createdAt"),
                        rs.getString("content"),
                        rs.getInt("category"),
                        rs.getInt("authorid")));
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

    static public Blog getBlogByID(String id) throws SQLException {
        String query = "select * from Blogs \n"
                + "where id=?";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = Model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Blog(rs.getInt("id"),
                        rs.getString("image"),
                        rs.getString("title"),
                        rs.getDate("createdAt"),
                        rs.getString("content"),
                        rs.getInt("category"),
                        rs.getInt("authorid"));
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

    static public List<Blog> getBlogbyAuthorID(int id) throws SQLException {
        List<Blog> list = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        String query = "SELECT * FROM Blogs WHERE authorId = ? ORDER BY id DESC";
        try {
            conn = Model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Blog(rs.getInt("id"),
                        rs.getString("image"),
                        rs.getString("title"),
                        rs.getDate("createdAt"),
                        rs.getString("content"),
                        rs.getInt("category"),
                        rs.getInt("authorid")));
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

    static public Blog addBlog(String image, String title, Date createdAt, String content, String category, int authorId) throws SQLException {

        PreparedStatement ps = null;

        Connection conn = null;

        try {

            String query = "INSERT INTO Blogs (image, title, createdAt, content, category, authorId) VALUES (?, ?, ?, ?, ?, ?)";

            conn = Model.getConnection();
            ps = conn.prepareStatement(query);

            ps.setString(1, image);
            ps.setString(2, title);
            ps.setDate(3, createdAt);
            ps.setString(4, content);
            ps.setString(5, category);
            ps.setInt(6, authorId);

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

}
