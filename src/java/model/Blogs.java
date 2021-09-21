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

/**
 *
 * @author Administrator
 */
public class Blogs {
    static public List<Blog> all() {
        List<Blog> list = new ArrayList<Blog>();
    
        String query = "SELECT * FROM Blogs";

        try {
            Connection conn = Model.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Blog(rs.getInt("id"), rs.getString("image"),rs.getString("title"),rs.getDate("createdAt"),rs.getString("content"),rs.getInt("category"),rs.getInt("authorid")));
            }
        } catch (Exception e) {}
        
        return list;
    }
    
    static public List<Blog> getBlogbyCategory(String category){
        List<Blog> list = new ArrayList<>();
        String query = "SELECT * FROM Blogs WHERE category = ?";
        try {
            Connection conn = Model.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, category);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Blog(rs.getInt("id"), rs.getString("image"),rs.getString("title"),rs.getDate("createdAt"),rs.getString("content"),rs.getInt("category"),rs.getInt("authorid")));
            }
        } catch (Exception e) {}
        
        return list;
    }
    static public List<Blog> getBlogbyUser(int user){
        List<Blog> list = new ArrayList<>();
        String query = "SELECT * FROM Blogs WHERE authorId = ?";
        try {
            Connection conn = Model.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, user);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Blog(rs.getInt("id"), rs.getString("image"),rs.getString("title"),rs.getDate("createdAt"),rs.getString("content"),rs.getInt("category"),rs.getInt("authorid")));
            }
        } catch (Exception e) {}
        
        return list;
    }
     
    static public List<Blog> search(String search){
        List<Blog> list = new ArrayList<>();
        String query = "SELECT * FROM Blogs WHERE title LIKE ?";
        try {
            Connection conn = Model.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "%" + search + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Blog(rs.getInt("id"), rs.getString("image"),rs.getString("title"),rs.getDate("createdAt"),rs.getString("content"),rs.getInt("category"),rs.getInt("authorid")));
            }
        } catch (Exception e) {}
        
        return list;
    }
    
    static public int total(){
        String query = "SELECT COUNT(*) FROM Blogs";
        try{
            Connection conn = Model.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                return rs.getInt(1);
            }
        }
        catch(Exception e){}
        
        return 0;
    }
    
    static public List<Blog> paging(int page){
        List<Blog> list = new ArrayList<>();
        String query="SELECT * FROM Blogs \n" +
                        "ORDER BY id \n" +
                        "OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
        try {
            Connection conn = Model.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, (page-1)*4);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Blog(rs.getInt("id"), rs.getString("image"),rs.getString("title"),rs.getDate("createdAt"),rs.getString("content"),rs.getInt("category"),rs.getInt("authorid")));
            }
        } catch (Exception e) {}
        
        return list;
    } 
    
    public static void main(String[] args) {
        List<Blog> list = Blogs.all();
        int c=Blogs.total();
        

    }
    
}
