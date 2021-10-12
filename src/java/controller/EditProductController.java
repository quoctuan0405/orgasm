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
import model.Product;
import model.Products;
import model.ProductCategory;
import model.ProductCategories;
import model.Users;

/**
 *
 * @author dangd
 */
@WebServlet(name = "EditProductController", urlPatterns = {"/product/edit"})
public class EditProductController extends HttpServlet {
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
        /***** Authentication *****/
        HttpSession session = request.getSession();

        if (session == null || session.getAttribute("acc") == null) {
            response.sendRedirect(request.getContextPath() + "/signup");
            return;
        }

        int userId = (int) session.getAttribute("acc");
        
        if (Users.findById(userId) == null) {
            response.sendRedirect(request.getContextPath() + "/signup");
            return;
        }
        /***** End Authentication *****/
        
        String productId = request.getParameter("id");       
        Product product = Products.getProductByID(productId);
        List<ProductCategory> categoryList = ProductCategories.allCategory();     
        
        request.setAttribute("product", product);
        request.setAttribute("categoryList", categoryList);
        request.getRequestDispatcher("/UpsertProduct.jsp").forward(request, response);
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
        /***** Authentication *****/
        HttpSession session = request.getSession();

        if (session == null || session.getAttribute("acc") == null) {
            response.sendRedirect(request.getContextPath() + "/signup");
            return;
        }

        int userId = (int) session.getAttribute("acc");
        
        if (Users.findById(userId) == null) {
            response.sendRedirect(request.getContextPath() + "/signup");
            return;
        }
        /***** End Authentication *****/
        
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String quantity = request.getParameter("quantity");
        String thumbnail = request.getParameter("picture");
        String description = request.getParameter("description");
        String category = request.getParameter("type");
        String id = request.getParameter("id");
            
        Products.editProduct(name, quantity, price, category, thumbnail, description, id);
        response.sendRedirect(request.getContextPath() + "/shop");
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
