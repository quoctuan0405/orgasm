/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.post;

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
import model.dao.Users;
import model.entity.Post;
import model.entity.ProductCategory;
import model.entity.User;

/**
 *
 * @author Puta
 */
@WebServlet(name = "EditPostController", urlPatterns = {"/editpost"})
public class EditPostController extends HttpServlet {

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
            out.println("<title>Servlet EditPostController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditPostController at " + request.getContextPath() + "</h1>");
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

        if (session == null || session.getAttribute("acc") == null) {
            response.sendRedirect(request.getContextPath() + "/signup");
            return;
        }

        int userId = (int) session.getAttribute("acc");
        
        User user = null;
        try {
            user = Users.findById(userId);
        } catch (SQLException ex) {
            Logger.getLogger(EditPostController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/signup");
            return;
        }
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        List<ProductCategory> listPostCategory = null;
        Post post = null;
        try {
            post = Posts.getPostByID(id);
            listPostCategory = ProductCategories.allCategory();
        } catch (SQLException ex) {
            Logger.getLogger(EditPostController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("listPostCategory", listPostCategory);
        request.setAttribute("post", post);
        request.setAttribute("user", user);
        request.setAttribute("type", "edit");
        
        request.getRequestDispatcher("PostManagement.jsp").forward(request, response);
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
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String category = request.getParameter("category");
        String id = request.getParameter("id");
            
        HttpSession session = request.getSession();

        if (session == null || session.getAttribute("acc") == null) {
            response.sendRedirect(request.getContextPath() + "/signup");
            return;
        }

        int userId = (int) session.getAttribute("acc");
        
        User user = null;
        try {
            user = Users.findById(userId);
        } catch (SQLException ex) {
            Logger.getLogger(EditPostController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/signup");
            return;
        }
        
        try {
            if (!title.isEmpty() && !content.isEmpty()){
                Posts.updatePost(title, content, category, id);
            } else {
                List<ProductCategory> listPostCategory = null;
                Post post = null;
                try {
                    post = Posts.getPostByID(id);
                    listPostCategory = ProductCategories.allCategory();
                } catch (SQLException ex) {
                    Logger.getLogger(EditPostController.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("listPostCategory", listPostCategory);
                request.setAttribute("post", post);
                request.setAttribute("user", user);
                request.setAttribute("type", "edit");
                if (title.isEmpty() && content.isEmpty()){
                    request.setAttribute("message", "You must input title and content");
                } else {
                    if (title.isEmpty()){
                        request.setAttribute("message", "You must input title");
                    } 
                    if (content.isEmpty()){
                        request.setAttribute("message", "You must input content");
                    }
                }
                request.getRequestDispatcher("PostManagement.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditPostController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.sendRedirect("mypost");  
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
