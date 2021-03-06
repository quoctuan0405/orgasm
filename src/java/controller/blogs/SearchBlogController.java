/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.blogs;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.entity.Blog;
import model.dao.BlogCategories;
import model.entity.BlogCategory;
import model.dao.Blogs;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "SearchBlogController", urlPatterns = {"/searchblog"})
public class SearchBlogController extends HttpServlet {
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
        // Comment
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
        
        String search = request.getParameter("search");
        
        List<Blog> listBlog = null;
        List<BlogCategory> listBlogCategory = null;
        try {
            listBlog = Blogs.search(search);
            listBlogCategory = BlogCategories.all();
        } catch (SQLException ex) {
            Logger.getLogger(SearchBlogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("listBlog", listBlog);
        request.setAttribute("listBlogCategory", listBlogCategory);
        
        request.getRequestDispatcher("Blog.jsp").forward(request, response);
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
