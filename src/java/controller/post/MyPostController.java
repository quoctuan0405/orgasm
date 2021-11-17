/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.post;

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
import model.dao.Posts;
import model.dao.ProductCategories;
import model.dao.Products;
import model.dao.Users;
import model.entity.Post;
import model.entity.ProductCategory;
import model.entity.User;

/**
 *
 * @author Puta
 */
@WebServlet(name = "MyPost", urlPatterns = {"/mypost"})
public class MyPostController extends HttpServlet {
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
            Logger.getLogger(MyPostController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/signup");
            return;
        }

        List<Post> listPost = null;
        List<ProductCategory> listPostCategory = null;
        try {
            listPost = Posts.getPostbyAuthorID(userId);
            listPostCategory = ProductCategories.allCategory();
        } catch (SQLException ex) {
            Logger.getLogger(MyPostController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("listPost", listPost);
        request.setAttribute("listPostCategory", listPostCategory);
        request.setAttribute("user", user);

        request.getRequestDispatcher("MyPost.jsp").forward(request, response);
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
    }

}
