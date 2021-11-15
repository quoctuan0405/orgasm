/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.post;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.dao.BlogCategories;
import model.entity.BlogCategory;
import model.entity.Post;
import model.entity.PostComment;
import model.dao.Posts;
import model.entity.User;
import model.dao.Users;

/**
 *
 * @author LAPTOP D&N
 */
@WebServlet(name = "PostDetailController", urlPatterns = {"/postdetail"})
public class PostDetailController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
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
        HttpSession session = request.getSession();
        
        try {
            String id = request.getParameter("id");
            Post post = Posts.getPostByID(id);
            List<PostComment> PostCommentList = Posts.getPostCommentByPostID(id);
            
            List<BlogCategory> listBlogCategory = BlogCategories.all();
            
            request.setAttribute("postdetail", post);
            request.setAttribute("PostCommentList", PostCommentList);
            request.setAttribute("listBlogCategory", listBlogCategory);
            
            int userId = (int) session.getAttribute("acc");
            User user = Users.findById(userId);
            request.setAttribute("user", user);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("PostDetail.jsp").forward(request, response);
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
