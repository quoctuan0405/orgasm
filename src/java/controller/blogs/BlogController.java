/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.blogs;

import java.io.IOException;
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
import model.BlogCategories;
import model.entity.BlogCategory;
import model.Blogs;
import model.entity.User;
import model.Users;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "BlogController", urlPatterns = {"/blog"})
public class BlogController extends HttpServlet {
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
        /* Pass user's information to jsp file if that user is logged in */
        try {
            HttpSession session = request.getSession();

            int userId = (int) session.getAttribute("acc");

            User user = Users.findById(userId);
            request.setAttribute("user", user);
            
        } catch (Exception ex) {
            Logger.getLogger(BlogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /* Pagination */
        String page = request.getParameter("page");
        if (page == null){
            page="1";
        }
        int indexPage = Integer.parseInt(page);
        
        int count = 0;
        try {
            count = Blogs.total();
        } catch (SQLException ex) {
            Logger.getLogger(BlogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        int endPage = count / 4;
        if (count % 4 != 0){
            endPage++;
        }
        
        List<Blog> listBlog = null;
        List<BlogCategory> listBlogCategory = null;
        try {
            listBlog = Blogs.paging(indexPage);
            listBlogCategory = BlogCategories.all();
        } catch (SQLException ex) {
            Logger.getLogger(BlogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("listBlog", listBlog);
        request.setAttribute("listBlogCategory", listBlogCategory);
        request.setAttribute("endPage", endPage);
        request.setAttribute("currentPage", page);
        
        request.getRequestDispatcher("/Blog.jsp").forward(request, response);
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
        // comment
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
