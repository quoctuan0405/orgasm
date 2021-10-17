/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.entity.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Roles extends Model {
    static public List<Role> all() throws SQLException {
        List<Role> list = new ArrayList<Role>();
    
        String query = "SELECT id, name FROM Roles";
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
             conn = Model.getConnection();
             ps = conn.prepareStatement(query);
             rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Role(rs.getInt("id"), rs.getString("name")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) {
                rs.close();
            }
            if(ps !=null) {
               ps.close();
            } 
            if(conn != null) {
               conn.close();
            }
        }
        
        return list;
    }
}
