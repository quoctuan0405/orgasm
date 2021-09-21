/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Blog;
import model.BlogCategories;
import model.BlogCategory;
import model.Blogs;

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
        String page = request.getParameter("page");
        if (page == null){
            page="1";
        }
        int indexPage = Integer.parseInt(page);
        
        int count = Blogs.total();
        int endPage = count / 4;
        if (count % 2 != 0){
            endPage++;
        }
        
        List<Blog> listBlog = Blogs.paging(indexPage);
        request.setAttribute("listBlog", listBlog);
        
        List<BlogCategory> listBlogCategory = BlogCategories.all();
        
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