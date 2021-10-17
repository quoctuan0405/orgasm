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
import utility.PasswordAuthentication;

/**
 *
 * @author Admin
 */
public class Users extends Model {

    static public List<User> all() throws SQLException {
        List<User> list = new ArrayList<User>();

        String query = "SELECT Users.id AS id, email, username, password, avatar, shortDescription, profile, address, phone, gender, status, emailVerified, verifyToken, Roles.name AS role "
                + "FROM Users "
                + "JOIN Roles ON Users.role = Roles.id";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = Model.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("profile"),
                        rs.getString("role"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("gender"),
                        rs.getString("status"),
                        rs.getString("password"),
                        rs.getString("avatar"),
                        rs.getString("shortDescription"),
                        rs.getBoolean("emailVerified"),
                        rs.getString("verifyToken")
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

    static public User findByUsername(String username) throws SQLException {
        String query = "SELECT Users.id AS id, email, username, password, avatar, shortDescription, profile, address, phone, gender, status, emailVerified, verifyToken, Roles.name AS role "
                + "FROM Users "
                + "JOIN Roles ON Users.role = Roles.id "
                + "WHERE username = ?";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = Model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);

            rs = ps.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("profile"),
                        rs.getString("role"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("gender"),
                        rs.getString("status"),
                        rs.getString("password"),
                        rs.getString("avatar"),
                        rs.getString("shortDescription"),
                        rs.getBoolean("emailVerified"),
                        rs.getString("verifyToken")
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

    static public User findById(int id) throws SQLException {
        String query = "SELECT Users.id AS id, email, username, password, profile, avatar, shortDescription, address, phone, gender, status, emailVerified, verifyToken, Roles.name AS role "
                + "FROM Users "
                + "JOIN Roles ON Users.role = Roles.id "
                + "WHERE Users.id = ?";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = Model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("profile"),
                        rs.getString("role"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("gender"),
                        rs.getString("status"),
                        rs.getString("password"),
                        rs.getString("avatar"),
                        rs.getString("shortDescription"),
                        rs.getBoolean("emailVerified"),
                        rs.getString("verifyToken")
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

    public static User verify(String username, String password) throws SQLException {
        User user = Users.findByUsername(username);
        if (user == null) {
            return null;
        }

        PasswordAuthentication passwordAuthentication = new PasswordAuthentication();
        if (passwordAuthentication.authenticate(password.toCharArray(), user.getHashedPassword())) {
            return user;
        } else {
            return null;
        }
    }

    public static User add(String email, String username, String password, int role, String verifyToken) throws SQLException {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            PasswordAuthentication passwordAuthentication = new PasswordAuthentication();
            String hashedPassword = passwordAuthentication.hash(password.toCharArray());

            String query = "INSERT INTO Users (email, username, password, role, verifyToken) VALUES (?, ?, ?, ?, ?)";

            conn = Model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, username);
            ps.setString(3, hashedPassword);
            ps.setInt(4, role);
            ps.setString(5, verifyToken);

            ps.executeUpdate();

            return Users.findByUsername(username);

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

    static public User updateProfile(String phone, String gender, String status, String shortDescription, String profile, String avatar, String address, int id) throws SQLException {
        String query = "UPDATE Users set  phone=?, gender=?, status=?, shortDescription=?, profile=?, avatar=?, address=?  where id = ?";

        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = Model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, phone);
            ps.setString(2, gender);
            ps.setString(3, status);
            ps.setString(4, shortDescription);
            ps.setString(5, profile);
            ps.setString(6, avatar);
            ps.setString(7, address);
            ps.setInt(8, id);
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

    public static boolean delete(int userId) throws SQLException {

        PreparedStatement ps = null;
        Connection conn = null;

        try {
            String query = "DELETE FROM Users WHERE id = ?";

            conn = Model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, userId);

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

    static public User changePassword(String username, String password) throws SQLException {

        PreparedStatement ps = null;
        Connection conn = null;

        try {
            PasswordAuthentication passwordAuthentication = new PasswordAuthentication();
            String hashedPassword = passwordAuthentication.hash(password.toCharArray());

            String query = "UPDATE Users SET password = ? WHERE username = ?";

            conn = Model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, hashedPassword);
            ps.setString(2, username);

            ps.executeUpdate();

            return Users.findByUsername(username);

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

    static public User verifyEmail(int id, String token) throws SQLException {

        PreparedStatement ps = null;
        Connection conn = null;

        try {
            User user = Users.findById(id);

            if (!user.getVerifyToken().equals(token)) {
                return null;
            }

            String query = "UPDATE Users SET emailVerified = 1, verifyToken = null WHERE id = ?";

            conn = Model.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);

            ps.executeUpdate();

            user.setEmailVerified(true);
            user.setVerifyToken(null);

            return user;

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

//    public static void main(String[] args) {
//        List<User> list = Users.all();
//        
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i).getAvatar());
//        }

//        User user1 = Users.findByUsername("username");
//        System.out.println(user1.getAddress());
//        
//        User user2 = Users.findById(1);
//        System.out.println(user2.getAddress());
//        
//        User user3 = Users.verify("username", "password");
//        System.out.println(user3.getHashedPassword());
//        User user4 = Users.add("someoneElse@email.com", "username1", "password", 1, "myverytoken");
//        System.out.println(user4.getRole());
//        Users.delete(3);
//        Users.changePassword("username", "password1");
//        User user5 = Users.verify("username", "password1");
//        System.out.println(user5.getAddress());
//    }
}
