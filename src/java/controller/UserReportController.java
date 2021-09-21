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
import model.CategoryProductStats;
import model.CategoryProducts;
import model.OrderStats;
import model.Orders;
import model.ProductStats;
import model.Products;
import model.Users;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UserReport", urlPatterns = {"/user/report"})
public class UserReportController extends HttpServlet {
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
        
        /***** Authentication *****/
        HttpSession session = request.getSession();

        if (session == null || session.getAttribute("acc") == null) {
            response.sendRedirect(request.getContextPath() + "/signup");
            return;
        }

        int userId = (int) session.getAttribute("acc");
        
        if (Users.findById(userId) == null) {
            response.sendRedirect(request.getContextPath() + "signup");
            return;
        }
        /***** End Authentication *****/
        
        List<OrderStats> revenueByMonth = Orders.revenueByMonth(1);
        List<CategoryProductStats> productsSoldByCategory = CategoryProducts.getCategoryProductStats(1);
        List<ProductStats> productsReport = Products.getProductStats(1); 
        
        request.setAttribute("revenueByMonth", revenueByMonth);
        request.setAttribute("productsSoldByCategory", productsSoldByCategory);
        request.setAttribute("productsReport", productsReport);
        
        request.getRequestDispatcher("/UserReport.jsp").forward(request, response);
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
