/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.post;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.dao.Posts;
import model.entity.User;
import model.dao.Users;

/**
 *
 * @author LAPTOP D&N
 */
@WebServlet(name = "PostCommentController", urlPatterns = {"/postcomment"})
public class PostCommentController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        
        int userId = (int) session.getAttribute("acc");
        User user = null;
        try {
            user = Users.findById(userId);
        } catch (SQLException ex) {
            Logger.getLogger(PostCommentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("user", user);
        
        
        
        int authorId = Integer.parseInt(request.getParameter("authorId"));
        int postId = Integer.parseInt(request.getParameter("postId"));
        
        long millis = System.currentTimeMillis();
        java.sql.Date createdAt = new java.sql.Date(millis);
        
        String content = request.getParameter("content");
        
        try {
            Posts.insertComment(authorId, postId, createdAt, content);
        } catch (SQLException e) {
            Logger.getLogger(PostCommentController.class.getName()).log(Level.SEVERE, null, e);
        }
        response.sendRedirect("postdetail?id=" + postId);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
