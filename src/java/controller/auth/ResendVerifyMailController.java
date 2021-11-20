/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.auth;

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
import model.dao.Users;
import model.entity.User;
import utility.Mail;
import utility.Token;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ResendVerifyMailController", urlPatterns = {"/email/verify/resend"})
public class ResendVerifyMailController extends HttpServlet {
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
        
        int userId = Integer.parseInt(request.getParameter("id"));
        
        try {
            if (Users.findById(userId) == null) {
                request.getRequestDispatcher("Signup.jsp");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResendVerifyMailController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            String token = Token.generateToken();

            User user = Users.setToken(userId, token);
            if (user == null) {
                request.setAttribute("error", "Username already exists.");
                request.getRequestDispatcher("Signup.jsp").forward(request, response);
                return;
            }

            HttpSession session = request.getSession();
            session.setAttribute("acc", user.getId());
            
            Mail.sendVerifyEmail("http://localhost:8080" + request.getContextPath() + "/mail/verify?token=" + token + "&userId=" + user.getId(), user.getEmail());

            request.getRequestDispatcher("/VerifyMail.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();

            request.setAttribute("error", "Username already exists.");
            request.getRequestDispatcher("/Signup.jsp").forward(request, response);
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
