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
import javax.servlet.http.HttpSession;
import model.BlogCategories;
import model.BlogCategory;
import model.Blogs;
import model.User;
import model.Users;

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
        
        User user = Users.findById(userId);
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/signup");
            return;
        }
        
        List<BlogCategory> listBlogCategory = BlogCategories.all();
        
        request.setAttribute("listBlogCategory", listBlogCategory);
        request.setAttribute("user", user);
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
        
        Blogs.addBlog(image, title, date, content, category, userId);
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
