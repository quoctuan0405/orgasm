/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.product;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.entity.Product;
import model.ProductCategories;
import model.entity.ProductCategory;
import model.Products;
import model.entity.User;
import model.Users;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ShopController", urlPatterns = {"/shop"})
public class ShopController extends HttpServlet {

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
        
        /* Pass user's information to jsp file if that user is logged in */
        try {
            HttpSession session = request.getSession();

            int userId = (int) session.getAttribute("acc");

            User user = Users.findById(userId);
            request.setAttribute("user", user);
            
        } catch (Exception e) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, "Unauthorized", e);
        }
        
        String page = request.getParameter("page");
        if (page == null){
            page="1";
        }
        int indexPage = Integer.parseInt(page);
        
        int count=0;
        try {
            count = Products.total();
        } catch (SQLException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
        int endPage = count / 4;
        if (count % 4 != 0){
            endPage++;
        }
        
        List<Product> productList = null;
        List<ProductCategory> categoryList = null;
        try {
            productList = Products.paging(indexPage);
            categoryList = ProductCategories.allCategory();
        } catch (SQLException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        List<Product> productList = Products.getProductByCreatorID();
        
        request.setAttribute("listP", productList);
        request.setAttribute("listC", categoryList);
        request.setAttribute("endPage", endPage);
        request.setAttribute("currentPage", page);
        
        request.getRequestDispatcher("/Shop.jsp").forward(request, response);
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
