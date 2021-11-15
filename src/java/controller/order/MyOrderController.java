/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.order;

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
import model.entity.Order;
import model.entity.OrderProduct;
import model.dao.Orders;
import model.entity.Product;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "MyOrderController", urlPatterns = {"/myorder"})
public class MyOrderController extends HttpServlet {
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
        HttpSession session = request.getSession(true);
        Object userId = session.getAttribute("acc");     
        
        List<Order> orderNum = null;
        List<Product> productList = null;
        List<OrderProduct> orderQuantity = null;
        
        try {
            orderNum = Orders.getOrderByUserid((int) userId);
            productList = Orders.getProductByBuyerId((int) userId);
            orderQuantity = Orders.getOrderProductByBuyerId((int) userId);

        } catch (SQLException ex) {
            Logger.getLogger(MyOrderController.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        request.setAttribute("OrderList",orderNum);
        request.setAttribute("ProductList", productList);
        request.setAttribute("Quantity", orderQuantity);        
        
        request.getRequestDispatcher("MyOrder.jsp").forward(request, response);
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
