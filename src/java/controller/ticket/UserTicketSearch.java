/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ticket;

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
import model.dao.Tickets;
import model.dao.Users;
import model.entity.Ticket;
import model.entity.User;

/**
 *
 * @author LAPTOP D&N
 */
@WebServlet(name = "UserTicketSearch", urlPatterns = {"/UserTicketSearch"})
public class UserTicketSearch extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserTicketSearch</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserTicketSearch at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        processRequest(request, response);
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
        HttpSession session = request.getSession();

        int userId = (int) session.getAttribute("acc");

        try {
            User user = Users.findById(userId);
            request.setAttribute("user", user);

        } catch (Exception e) {
            Logger.getLogger(SearchTicketController.class.getName()).log(Level.SEVERE, "Unauthorized", e);
        }

        String txtSearch = request.getParameter("search");

        List<Ticket> ticketList = null;

        try {
            ticketList = Tickets.UserTicketSearch(txtSearch, userId);
        } catch (SQLException ex) {
            Logger.getLogger(SearchTicketController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("ticketList", ticketList);
        request.getRequestDispatcher("Ticket.jsp").forward(request, response);
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
