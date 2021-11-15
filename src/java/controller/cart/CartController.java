/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.cart;

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
import model.entity.ProductInCart;
import model.dao.Cart;
import model.entity.Product;
import model.dao.Products;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "CartController", urlPatterns = {"/cart"})
public class CartController extends HttpServlet {
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

        Cart cart = null;
        Object o = session.getAttribute("cart");
        //co roi
        if (o != null) {
            cart = (Cart) o;
        } else {
            cart = new Cart();

        }

        try {
            String tnum = request.getParameter("num").trim();
            int id = Integer.parseInt(request.getParameter("id"));
            int num, idInt;
            
            idInt = id;
            Product p = Products.getProductByID(id);
            int realnum = p.getQuantity();
            num = Integer.parseInt(tnum);
            if ((num == -1) && (cart.getQuantityById(idInt) <= 1)) {
                cart.removeFromCart(idInt);
            } else {
                if ((num == 1) && cart.getQuantityById(idInt) >= realnum) {
                    num = 0;
                }
                double price = p.getPrice();
                ProductInCart t = new ProductInCart(p, num, price);
                cart.addToCart(t);
            }

        } catch (SQLException e) {
            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, e);
            request.getRequestDispatcher("Cart.jsp").forward(request, response);
            return;
            
        } catch (Exception e) {
            request.getRequestDispatcher("Cart.jsp").forward(request, response);
            return; 
       }

        List<ProductInCart> list = cart.getProducts();
        session.setAttribute("cart", cart);
        session.setAttribute("size", list.size());
        request.getRequestDispatcher("Cart.jsp").forward(request, response);
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
        HttpSession session =request.getSession(true);
        Cart cart=null;
        Object o=session.getAttribute("cart");
        //co roi
        if(o!=null){
            cart=(Cart)o;
        }else{
            cart=new Cart();
        }
        int id=Integer.parseInt(request.getParameter("id"));
        cart.removeFromCart(id);
        
        List<ProductInCart> list=cart.getProducts();
        
        session.setAttribute("cart", cart);
        session.setAttribute("size", list.size());
        request.getRequestDispatcher("Cart.jsp").forward(request, response);
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
