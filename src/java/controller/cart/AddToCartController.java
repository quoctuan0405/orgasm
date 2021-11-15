/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.entity.ProductInCart;
import model.dao.Cart;
import model.entity.Product;
import model.dao.Products;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "AddToCartController", urlPatterns = {"/addtocart"})
public class AddToCartController extends HttpServlet {
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
        // comment
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
        HttpSession session = request.getSession(true);
        Cart cart = null;
        Object o = session.getAttribute("cart");

        //co roi
        if (o != null) {
            cart = (Cart) o;
        } else {
            cart = new Cart();
        }
        
        int productId = Integer.parseInt(request.getParameter("productId"));
        
        int productQuantity = 1;
        try{
           productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
           
           Product product = Products.getProductByID(productId);
           double price = product.getPrice();
           
           ProductInCart t = new ProductInCart(product,productQuantity, price);
           cart.addToCart(t);

        } catch (NumberFormatException e){
            productQuantity = 1;

        } catch (SQLException e) {
            System.out.println(e);
            Logger.getLogger(AddToCartController.class.getName()).log(Level.SEVERE, null, e);
        } 
            
        List<ProductInCart> productInCartList = cart.getProducts();
        
        session.setAttribute("cart", cart);
        session.setAttribute("size", productInCartList.size());
        
        request.getRequestDispatcher("/Cart.jsp").forward(request, response);
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
