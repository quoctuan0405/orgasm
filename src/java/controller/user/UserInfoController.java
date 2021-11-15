/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.user;

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
import model.dao.Blogs;
import model.dao.Products;
import model.entity.Blog;
import model.entity.Product;
import model.entity.User;
import model.dao.Users;

/**
 *
 * @author LAPTOP D&N
 */
@WebServlet(name = "UserInfoController", urlPatterns = {"/userInfo"})
public class UserInfoController extends HttpServlet {
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

        System.out.println(request.getParameter("id"));
        int userId = Integer.parseInt(request.getParameter("id"));
        

        User user = null;
        List<Blog> blog = null;
        List<Product> product = null;
        try {
            user = Users.getUserByProduct(userId);
            blog = Blogs.getBlogByUser(userId);
            product = Products.getProductByUser(userId);
        } catch (SQLException ex) {
            Logger.getLogger(UserInfoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("user", user);
        request.setAttribute("blog", blog);
        request.setAttribute("product", product);

        request.getRequestDispatcher("UserInfo.jsp").forward(request, response);
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
