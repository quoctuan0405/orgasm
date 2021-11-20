/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.review;

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
import model.entity.Product;
import model.entity.ProductFeedback;
import model.dao.ProductFeedbacks;
import model.entity.User;
import model.dao.Users;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "ReviewController", urlPatterns = {"/review"})
public class ReviewController extends HttpServlet {
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
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("oid");
        int orderProductId = Integer.parseInt(id);
        request.setAttribute("oid", orderProductId);

        int productId = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("id", productId);

        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("acc");
        User user = null;
        try {
            user = Users.findById(userId);
        } catch (SQLException ex) {
            Logger.getLogger(ReviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("user", user);

        Product product = ProductFeedbacks.getProductByOrderProductId(orderProductId);
        request.setAttribute("Product", product);

        int avg = ProductFeedbacks.getRating(productId);
        request.setAttribute("avg", avg);

        List<ProductFeedback> feedbackList = null;
        try {
            feedbackList = ProductFeedbacks.getProductFeedbacks(productId);
        } catch (SQLException ex) {
            Logger.getLogger(ReviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("feedbackList", feedbackList);

        request.getRequestDispatcher("Review.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        
        String des = request.getParameter("des");
        int oid = Integer.parseInt(request.getParameter("oid"));
        int id = Integer.parseInt(request.getParameter("id"));
        int rate = Integer.parseInt(request.getParameter("rate"));
        
        try {
            ProductFeedbacks.addFeedback(oid, rate, des);
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(ReviewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.sendRedirect(request.getContextPath() + "/review?oid=" + oid +"&id="+ id);
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
