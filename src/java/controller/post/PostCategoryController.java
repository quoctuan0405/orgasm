/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.post;

import controller.product.MyProductController;
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
import model.dao.Users;
import model.entity.Post;
import model.entity.ProductCategory;
import model.entity.User;

/**
 *
 * @author dangd
 */
@WebServlet(name = "PostCategoryController", urlPatterns = {"/postCate"})
public class PostCategoryController extends HttpServlet {

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
        
        try {
            HttpSession session = request.getSession();

            int userId = (int) session.getAttribute("acc");

            User user = Users.findById(userId);
            request.setAttribute("user", user);
            
        } catch (Exception e) {
            Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, "Unauthorized", e);
        }

        String cateID = request.getParameter("id");

        List<Post> postListByCateID = null;
        List<ProductCategory> categoryList = null;

        try {
            postListByCateID = Posts.getPostByCateID(cateID);
        } catch (SQLException ex) {
            Logger.getLogger(PostCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            categoryList = ProductCategories.allCategory();
        } catch (SQLException ex) {
            Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("postList", postListByCateID);
        request.setAttribute("categoryList", categoryList);
        request.getRequestDispatcher("Post.jsp").forward(request, response);
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
