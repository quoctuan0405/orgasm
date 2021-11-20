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
import model.entity.PostComment;
import model.entity.Post;
import model.entity.ProductCategory;

/**
 *
 * @author dangd
 */
public class Posts {

    static private IModel model = new Model();

    static public void setModel(IModel alternativeModel) {
        model = alternativeModel;
    }

    static public List<Post> getPostbyAuthorID(int id) throws SQLException {
        List<Post> list = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        String query = "SELECT * FROM Posts WHERE authorId = ? ORDER BY id DESC";
        try {
            conn = model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Post(rs.getInt("id"),
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

    static public Post addPost(String title, Date createdAt, String content, String category, int authorId) throws SQLException {
        PreparedStatement ps = null;
        Connection conn = null;

        try {

            String query = "INSERT INTO Posts (title, createdAt, content, category, authorId) VALUES (?, ?, ?, ?, ?)";

            conn = model.getConnection();
            ps = conn.prepareStatement(query);

            ps.setString(1, title);
            ps.setDate(2, createdAt);
            ps.setString(3, content);
            ps.setString(4, category);
            ps.setInt(5, authorId);

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

    static public void updatePost(String title, String content, String category, String id) throws SQLException {
        String query = "UPDATE Posts set title=?, content=?, category=?  where id = ?";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = model.getConnection();
            ps = conn.prepareStatement(query);

            ps.setString(1, title);
            ps.setString(2, content);
            ps.setString(3, category);
            ps.setString(4, id);
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

    public static boolean delete(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            String deletePostQuery = "DELETE FROM Posts WHERE id = ?";

            conn = model.getConnection();
            ps = conn.prepareStatement(deletePostQuery);
            ps.setString(1, id);

            ps.executeUpdate();

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;

        } finally {
            if (ps != null) {
                ps.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
    }

    static public Post getPostByID(int id) throws SQLException {
        String query = "select * from Posts \n"
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
                return new Post(rs.getInt("id"),
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

    static public List<ProductCategory> allCategory() throws SQLException {
        List<ProductCategory> list = new ArrayList<ProductCategory>();

        String query = "SELECT * FROM ProductCategories";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = model.getConnection();
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

    public static List<Post> getAllPosts() throws SQLException {
        List<Post> list = new ArrayList<Post>();
        String query = "select Users.id, avatar, username, title, ProductCategories.id as cateID, ProductCategories.name as cateName\n"
                + "from Posts\n"
                + "join Users on Users.id = Posts.authorId\n"
                + "join ProductCategories on Posts.category = ProductCategories.id";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = model.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Post(rs.getInt("id"),
                        rs.getString("avatar"),
                        rs.getString("username"),
                        rs.getString("title"),
                        rs.getInt("cateID"),
                        rs.getString("cateName"))
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

    public static List<Post> getPostByCateID(String cateID) throws SQLException {
        List<Post> list = new ArrayList<Post>();
        String query = "select Users.id, avatar, username, title, ProductCategories.id as cateID, ProductCategories.name as cateName\n"
                + "from Posts\n"
                + "join Users on Users.id = Posts.authorId\n"
                + "join ProductCategories on Posts.category = ProductCategories.id\n"
                + "where ProductCategories.id = ?";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, cateID);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Post(rs.getInt("id"),
                        rs.getString("avatar"),
                        rs.getString("username"),
                        rs.getString("title"),
                        rs.getInt("cateID"),
                        rs.getString("cateName"))
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

    static public List<Post> searchPosts(String txtSearch) throws SQLException {
        List<Post> list = new ArrayList<Post>();

        String query = "select Users.id, avatar, username, title, ProductCategories.id as cateID, ProductCategories.name as cateName"
                + " from Posts\n"
                + "join Users on Users.id = Posts.authorId\n"
                + "join ProductCategories on ProductCategories.id = Posts.category\n"
                + "where Users.username like ?\n"
                + "or Posts.content like ?\n"
                + "or Posts.title like ?\n"
                + "or ProductCategories.name like ?";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%");
            ps.setString(2, "%" + txtSearch + "%");
            ps.setString(3, "%" + txtSearch + "%");
            ps.setString(4, "%" + txtSearch + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Post(rs.getInt("id"),
                        rs.getString("avatar"),
                        rs.getString("username"),
                        rs.getString("title"),
                        rs.getInt("cateID"),
                        rs.getString("cateName"))
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

    static public int total() {
        String query = "select COUNT(*) from Posts";
        try {
            Connection conn = model.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }

        return 0;
    }

    static public List<Post> paging(int page) {
        List<Post> list = new ArrayList<>();
        String query = "select Posts.id, avatar, username, title, ProductCategories.id as cateID, ProductCategories.name as cateName\n"
                + "from Posts\n"
                + "join Users on Users.id = Posts.authorId\n"
                + "join ProductCategories on Posts.category = ProductCategories.id\n"
                + "Order by Posts.id\n"
                + "LIMIT ?, 5";
        try {
            Connection conn = model.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, (page - 1) * 5);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Post(rs.getInt("id"),
                        rs.getString("avatar"),
                        rs.getString("username"),
                        rs.getString("title"),
                        rs.getInt("cateID"),
                        rs.getString("cateName"))
                );
            }
        } catch (Exception e) {
        }

        return list;
    }
    
    static public Post getPostByID(String id) throws SQLException {

        String query = "select * from Posts\n"
                + "join Users on Users.id = Posts.id\n"
                + "where Posts.id = ?";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Post(rs.getInt("id"),
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

    static public List<PostComment> getPostCommentByPostID(String postId) throws SQLException {
        List<PostComment> list = new ArrayList<>();
        String query = "select * from PostComment\n"
                + "JOIN Users ON Users.id = PostComment.authorId\n"
                + "where postId = ?";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, postId);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new PostComment(rs.getInt("id"),
                        rs.getInt("authorId"),
                        rs.getInt("postId"),
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

    static public Comment insertComment(int authorId, int postId, Date createdAt, String content) throws SQLException {

        PreparedStatement ps = null;
        Connection conn = null;

        try {
            String query = "INSERT INTO PostComment\n"
                    + "(authorId, postId, createdAt, content)\n"
                    + "VALUES (?,?,?,?)";

            conn = model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, authorId);
            ps.setInt(2, postId);
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
//        List<Port> list = Posts.getPostByCateID("1");
//        for (Post o : list) {
//            System.out.println(o);
//        }

//        List<Port> list1 = Posts.searchPosts("tomato");
//        for (Post o : list1) {
//            System.out.println(o);
//        }
        List<Post> list2 = Posts.paging(1);
        for (Post o : list2) {
            System.out.println(o);
        }

    }

}
