/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import controller.user.UserReportController;
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
import model.dao.Orders;
import model.dao.ProductCategories;
import model.dao.Products;
import model.dao.Roles;
import model.entity.User;
import model.dao.Users;
import model.entity.OrderStats;
import model.entity.ProductCategoryStats;
import model.entity.ProductStats;
import model.entity.Role;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AdminDashboardController", urlPatterns = {"/admin/dashboard"})
public class AdminDashboardController extends HttpServlet {
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

        /**
         * *** Authentication ****
         */
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
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (user == null || !user.getRole().equals("admin")) {
            response.sendRedirect(request.getContextPath() + "/signup");
            return;
        }
        /**
         * *** End Authentication ****
         */

        List<User> users = null;
        List<Role> roles = null;
        try {
            users = Users.all();
            roles = Roles.all();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("users", users);
        request.setAttribute("roles", roles);
        
        List<ProductStats> productsReport = null;
        try {
            productsReport = Products.getProductStatsAdmin();
            for (int i = 0; i < productsReport.size(); i++) {
                System.out.println(productsReport.get(i).getName());
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("productsReport", productsReport);

        request.getRequestDispatcher("/AdminDashboard.jsp").forward(request, response);
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
