/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.comment;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.dao.Comments;
import model.entity.User;
import model.dao.Users;

/**
 *
 * @author LAPTOP D&N
 */
@WebServlet(name = "AddCommentController", urlPatterns = {"/add_comment"})
public class AddCommentController extends HttpServlet {
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
        //comment

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
            Logger.getLogger(AddCommentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("user", user);

        int authorId = Integer.parseInt(request.getParameter("authorId"));
        int blogId = Integer.parseInt(request.getParameter("blogId"));
        
        long millis = System.currentTimeMillis();
        java.sql.Date createdAt = new java.sql.Date(millis);
        
        String content = request.getParameter("content");

        try {
            Comments.insertComment(authorId, blogId, createdAt, content);
        } catch (SQLException e) {
            Logger.getLogger(AddCommentController.class.getName()).log(Level.SEVERE, null, e);
        }

        response.sendRedirect("blogdetail?id=" + blogId);
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
