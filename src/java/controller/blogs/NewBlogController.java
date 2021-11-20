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
import model.dao.BlogCategories;
import model.entity.BlogCategory;
import model.dao.Blogs;
import model.entity.User;
import model.dao.Users;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "NewBlogController", urlPatterns = {"/addblog"})
public class NewBlogController extends HttpServlet {
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
            Logger.getLogger(NewBlogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/signup");
            return;
        }
        
        List<BlogCategory> listBlogCategory = null;
        try {
            listBlogCategory = BlogCategories.all();
        } catch (SQLException ex) {
            Logger.getLogger(NewBlogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("listBlogCategory", listBlogCategory);
        request.setAttribute("user", user);
        request.setAttribute("type", "add");
        request.getRequestDispatcher("/UpsertBlog.jsp").forward(request, response);
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
        String title = request.getParameter("title");
        String image = request.getParameter("image");
        String category = request.getParameter("category");
        String content = request.getParameter("content");
        
        long millis = System.currentTimeMillis();   
        java.sql.Date date=new java.sql.Date(millis);
        HttpSession session = request.getSession();
        
        int userId = (int) session.getAttribute("acc");
        try {
            Blogs.addBlog(image, title, date, content, category, userId);
        } catch (SQLException ex) {
            Logger.getLogger(NewBlogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("myblog");
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
