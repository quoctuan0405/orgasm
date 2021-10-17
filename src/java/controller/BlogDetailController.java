/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Blogs;
import model.Blog;
import model.BlogCategories;
import model.BlogCategory;
import model.User;
import model.Users;

/**
 *
 * @author LAPTOP D&N
 */
@WebServlet(name = "BlogDetail", urlPatterns = {"/blogdetail"})
public class BlogDetailController extends HttpServlet {
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
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        
        try {
            String id = request.getParameter("id");
            Blog blog = Blogs.getBlogByID(id);
            
            List<BlogCategory> listBlogCategory = BlogCategories.all();

            request.setAttribute("blogdetail", blog);
            request.setAttribute("listBlogCategory", listBlogCategory);

            /* Pass the logged in user to the jsp */
            int userId = (int) session.getAttribute("acc");
            User user = Users.findById(userId);
            
            request.setAttribute("user", user);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        request.getRequestDispatcher("BlogDetail.jsp").forward(request, response);
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
        //comment   
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
