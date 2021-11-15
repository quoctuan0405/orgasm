/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.entity.Blog;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.IModel;
import model.Model;

/**
 *
 * @author Administrator
 */
public class Blogs {
    static private IModel model = new Model();
    
    static public void setModel(IModel alternativeModel) {
        model = alternativeModel;
    }
    
    static public List<Blog> all() throws SQLException {
        List<Blog> list = new ArrayList<Blog>();

        String query = "SELECT * FROM Blogs";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = model.getConnection();
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
            conn = model.getConnection();
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
            conn = model.getConnection();
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

    static public List<Blog> paging(int page) throws SQLException {
        List<Blog> list = new ArrayList<>();
        String query = "SELECT * FROM Blogs \n"
                + "ORDER BY id \n"
                + "OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = model.getConnection();
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

    static public Blog getBlogByID(int id) throws SQLException {
        String query = "select * from Blogs \n"
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
                return new Blog(rs.getInt("id"),
                        rs.getString("image"),
                        rs.getString("title"),
                        rs.getDate("createdAt"),
                        rs.getString("content"),
                        rs.getInt("category"),
                        rs.getInt("authorId"));
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
            conn = model.getConnection();
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

            conn = model.getConnection();
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

    static public void updateBlog(String image, String title, String content, String category, String id) throws SQLException {
        String query="UPDATE Blogs set  image=?, title=?, content=?, category=?  where id = ?";
        
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = model.getConnection();
            ps = conn.prepareStatement(query);
            
            ps.setString(1, image);
            ps.setString(2, title);
            ps.setString(3, content);
            ps.setString(4, category);
            ps.setString(5, id);
            ps.executeUpdate();
           
        } catch (Exception e){
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
    
    public static boolean delete(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement psBlog = null;
        PreparedStatement psComment = null;
            
        try {
            String deleteAssociatedCommentQuery = "DELETE FROM BlogComments WHERE blogId = ?";
            String deleteBlogQuery = "DELETE FROM Blogs WHERE id = ?";
            
            conn = model.getConnection();
            psBlog = conn.prepareStatement(deleteBlogQuery);
            psComment = conn.prepareStatement(deleteAssociatedCommentQuery);
            psBlog.setString(1, id);
            psComment.setString(1, id);
            
            psComment.executeUpdate();
            psBlog.executeUpdate();
            
            return true;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
            
        } finally {
            if (psBlog != null) {
                psBlog.close();
            }
            
            if (psComment != null) {
                psComment.close();
            }
            
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    static public List<Blog> getBlogByUser(int id) throws SQLException {

        List<Blog> list = new ArrayList<>();

        String query = "select * from Blogs\n"
                + "JOIN Users ON Users.id = Blogs.authorid\n"
                + "WHERE authorid=?";

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        try {

            conn = model.getConnection();
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
    
    public static void main(String[] args) throws SQLException {
        List<Blog> blogs = Blogs.all();
        
        for (int i = 0; i < blogs.size(); i++) {
            System.out.println(blogs.get(i).getId());
        }
    }
}
