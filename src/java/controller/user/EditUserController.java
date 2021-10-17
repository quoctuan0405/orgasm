/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.entity.User;
import model.Users;

/**
 *
 * @author Admin
 */
@WebServlet(name = "EditUserController", urlPatterns = {"/admin/user/edit"})
public class EditUserController extends HttpServlet {
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
        
        /**** Check if user is admin or not ****/
        HttpSession session = request.getSession();

        if (session == null || session.getAttribute("acc") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int userLoggedInId = (int) session.getAttribute("acc");

        User userLoggedIn = null;
        try {
            userLoggedIn = Users.findById(userLoggedInId);
        } catch (SQLException ex) {
            Logger.getLogger(EditUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (userLoggedIn == null || !userLoggedIn.getRole().equals("admin")) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        /**** End check ****/
        
        try {
            int userEditId = Integer.parseInt(request.getParameter("userId"));
            
            User userEdit = Users.findById(userEditId);
            
            request.setAttribute("user", userEdit);
            request.setAttribute("admin", "admin");
        
            request.getRequestDispatcher("/EditUser.jsp").forward(request, response);

        } catch(Exception e) {
            response.sendRedirect("/admin/dashboard");
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
        response.setContentType("text/html;charset=UTF-8");
        
        /**** Check if user is admin or not ****/
        HttpSession session = request.getSession();

        if (session == null || session.getAttribute("acc") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int userLoggedInId = (int) session.getAttribute("acc");

        User userLoggedIn = null;
        try {
            userLoggedIn = Users.findById(userLoggedInId);
        } catch (SQLException ex) {
            Logger.getLogger(EditUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (userLoggedIn == null || userLoggedIn.getRole().equals("admin")) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        /**** End check ****/
        
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            String avatar = request.getParameter("avatar");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String gender = request.getParameter("gender");
            String status = request.getParameter("status");
            String shortDescription = request.getParameter("shortDescription");
            String profile = request.getParameter("profile");

            Users.updateProfile(phone, gender, status, shortDescription, profile, avatar, address, userId);
            
        } catch(Exception e) {
            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
        }
        
        response.sendRedirect(request.getContextPath() + "/admin/dashboard");
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
