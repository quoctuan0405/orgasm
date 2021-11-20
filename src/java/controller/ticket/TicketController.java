/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ticket;

import controller.product.ShopController;
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
import model.dao.Products;
import model.dao.Tickets;
import model.dao.Users;
import model.entity.Post;

import model.entity.ProductCategory;
import model.entity.Ticket;
import model.entity.User;

/**
 *
 * @author LAPTOP D&N
 */
@WebServlet(name = "TicketController", urlPatterns = {"/ticket"})
public class TicketController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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

        int userId = (int) session.getAttribute("acc");

        try {

            User user = Users.findById(userId);
            request.setAttribute("user", user);

        } catch (Exception e) {
            Logger.getLogger(TicketController.class.getName()).log(Level.SEVERE, "Unauthorized", e);
        }

        String page = request.getParameter("page");
        if (page == null) {
            page = "1";
        }
        int indexPage = Integer.parseInt(page);

        int count = 0;
        try {
            count = Tickets.totalTicketbyAuthor(userId);
        } catch (SQLException ex) {
            Logger.getLogger(TicketController.class.getName()).log(Level.SEVERE, null, ex);
        }
        int endPage = count / 5;
        if (count % 5 != 0) {
            endPage++;
        }

        List<Ticket> ticketList = null;

        try {
            ticketList = Tickets.paging(userId, indexPage);
        } catch (SQLException ex) {
            Logger.getLogger(TicketController.class.getName()).log(Level.SEVERE, null, ex);
        }


        request.setAttribute("ticketList", ticketList);
        request.setAttribute("endPage", endPage);
        request.setAttribute("currentPage", page);
        request.getRequestDispatcher("Ticket.jsp").forward(request, response);
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
