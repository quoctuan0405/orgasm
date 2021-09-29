/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import model.Users;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ChangePasswordController", urlPatterns = {"/password/change"})
public class ChangePasswordController extends HttpServlet {

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
        /**** Check if user logged in ****/
        HttpSession session = request.getSession();

        if (session == null || session.getAttribute("acc") == null) {
            response.sendRedirect(request.getContextPath() + "/signup");
            return;
        }

        int userId = (int) session.getAttribute("acc");
        
        User user = Users.findById(userId);
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/signup");
            return;
        }
        /***** End authentication ****/
        
        request.getRequestDispatcher("/ChangePassword.jsp").forward(request, response);
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
        /**** Check if user logged in ****/
        HttpSession session = request.getSession();

        if (session == null || session.getAttribute("acc") == null) {
            response.sendRedirect(request.getContextPath() + "/signup");
            return;
        }

        int userId = (int) session.getAttribute("acc");
        
        User user = Users.findById(userId);
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/signup");
            return;
        }
        /***** End authentication ****/
        
        try {
            String oldPassword = request.getParameter("oldPassword");
            String newPassword = request.getParameter("newPassword");
            
            if (Users.verify(user.getUsername(), oldPassword) ==  null) {
                request.setAttribute("error", "Incorrect password");
                request.getRequestDispatcher("/ChangePassword.jsp").forward(request, response);
            }
            
            Users.changePassword(user.getUsername(), newPassword);
            
            response.sendRedirect(request.getContextPath() + "/profile");
            
        } catch (Exception e) {
            request.setAttribute("error", "Incorrect password");
            request.getRequestDispatcher("/ChangePassword.jsp").forward(request, response);
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
