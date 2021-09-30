/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import model.Users;
import utility.Mail;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CheckMailController", urlPatterns = {"/mail/verify"})
public class VerifyMailController extends HttpServlet {
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
        
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            String token = request.getParameter("token");
            
            User user = Users.verifyEmail(userId, token);
            if (user == null) {
                request.setAttribute("invalidToken", "invalidToken");
                request.getRequestDispatcher("/VerifyMail.jsp").forward(request, response);
            }
            
            HttpSession session = request.getSession();
            session.setAttribute("acc", user.getId());

            response.sendRedirect(request.getContextPath() + "/profile/edit");
            
        } catch (Exception e) {
            request.setAttribute("invalidToken", "invalidToken");
            request.getRequestDispatcher("/VerifyMail.jsp").forward(request, response);
        }
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
