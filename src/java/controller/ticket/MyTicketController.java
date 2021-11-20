/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ticket;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import model.entity.User;

/**
 *
 * @author LAPTOP D&N
 */
@WebServlet(name = "MyTicketController", urlPatterns = {"/myticket"})
public class MyTicketController extends HttpServlet {

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
            out.println("<title>Servlet MyTicket</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MyTicket at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        User user = null;
        
        try {
            user = Users.findById(userId);
        } catch (SQLException ex) {
            Logger.getLogger(TicketCommentController.class.getName()).log(Level.SEVERE, null, ex);            
        }
        request.setAttribute("user", user);
        
        request.getRequestDispatcher("AddTicket.jsp").forward(request, response);
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
        User user = null;
        
        try {
            user = Users.findById(userId);
        } catch (SQLException ex) {
            Logger.getLogger(TicketCommentController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        request.setAttribute("user", user);
        System.out.println(request.getParameter("authorId"));
        int authorId = Integer.parseInt(request.getParameter("authorId"));
        
        String content = request.getParameter("content");
        long millis = System.currentTimeMillis();
        java.sql.Date createdAt = new java.sql.Date(millis);
        String title = request.getParameter("title");
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaa");
        try {
            Tickets.addTicket(authorId, createdAt, content, title);
        } catch (SQLException ex) {
            Logger.getLogger(TicketCommentController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        
        response.sendRedirect("ticket");
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
