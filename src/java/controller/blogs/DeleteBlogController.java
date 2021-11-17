/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.blogs;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.entity.Blog;
import model.dao.BlogCategories;
import model.entity.BlogCategory;
import model.dao.Blogs;
import model.entity.User;
import model.dao.Users;

/**
 *
 * @author Puta
 */
@WebServlet(name = "DeleteBlogController", urlPatterns = {"/deleteblog"})
public class DeleteBlogController extends HttpServlet {
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
        HttpSession session = request.getSession();

        if (session == null || session.getAttribute("acc") == null) {
            response.sendRedirect(request.getContextPath() + "/signup");
            return;
        }

        int userId = (int) session.getAttribute("acc");
        
        User user = null;
        try {
            user = Users.findById(userId);
        } catch (SQLException ex) {
            Logger.getLogger(DeleteBlogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/signup");
            return;
        }
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        Blog blog = null;
        List<BlogCategory> listBlogCategory = null;
        
        try {
            listBlogCategory = BlogCategories.all();
            blog = Blogs.getBlogByID(id);
        } catch (SQLException ex) {
            Logger.getLogger(DeleteBlogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("user", user);
        request.setAttribute("listBlogCategory", listBlogCategory);
        request.setAttribute("blog", blog);
        request.setAttribute("type", "delete");
        
        request.getRequestDispatcher("/ManageBlog.jsp").forward(request, response);
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
        
        String id = request.getParameter("id");
        
        try {
            Blogs.delete(id);
        } catch (SQLException ex) {
            Logger.getLogger(DeleteBlogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.sendRedirect(request.getContextPath() + "/myblog"); 
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
