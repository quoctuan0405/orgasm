/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.entity.User;
import model.dao.Users;
import utility.Mail;
import utility.Token;

/**
 *
 * @author Admin
 */
@WebServlet(name = "SignupController", urlPatterns = {"/signup"})
public class SignupController extends HttpServlet {
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
        
        request.getRequestDispatcher("Signup.jsp").forward(request, response);
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
        
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (email == null || username == null || password == null || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            request.setAttribute("error", "Password and username must not be empty.");
            request.getRequestDispatcher("Signup.jsp").forward(request, response);
        }

        try {
            String token = Token.generateToken();

            User user = Users.add(email, username, password, 1, token); // 1 means user role
            if (user == null) {
                request.setAttribute("error", "Username already exists.");
                request.getRequestDispatcher("Signup.jsp").forward(request, response);
            }

            HttpSession session = request.getSession();
            session.setAttribute("acc", user.getId());
            
            Mail.sendVerifyEmail("http://localhost:8080" + request.getContextPath() + "/mail/verify?token=" + token + "&userId=" + user.getId(), email);

            request.setAttribute("user", user);
            request.getRequestDispatcher("/VerifyMail.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();

            request.setAttribute("error", "Username already exists.");
            request.getRequestDispatcher("Signup.jsp").forward(request, response);
        }
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
