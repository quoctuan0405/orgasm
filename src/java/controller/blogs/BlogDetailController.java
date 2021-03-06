/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.blogs;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.dao.Blogs;
import model.entity.Blog;
import model.dao.BlogCategories;
import model.Comment;
import model.dao.Comments;
import model.entity.BlogCategory;
import model.entity.User;
import model.dao.Users;

/**
 *
 * @author LAPTOP D&N
 */
@WebServlet(name = "BlogDetail", urlPatterns = {"/blogdetail"})
public class BlogDetailController extends HttpServlet {
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
        
        HttpSession session = request.getSession();

        try {

            int id = Integer.parseInt(request.getParameter("id"));
            System.out.println(id);
            Blog blog = Blogs.getBlogByID(id);

            List<Comment> commentList = Comments.getCommentByBlogID(id);
            
            commentList.forEach((o) -> {
                System.out.println(o);
                System.out.println("Ass");
            });              
            
            List<BlogCategory> listBlogCategory = BlogCategories.all();

            request.setAttribute("blogdetail", blog);

            request.setAttribute("blogcomment", commentList);

            request.setAttribute("listBlogCategory", listBlogCategory);

            /* Pass the logged in user to the jsp */
            int userId = (int) session.getAttribute("acc");

            User user = Users.findById(userId);

            request.setAttribute("user", user);

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("BlogDetail.jsp").forward(request, response);
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
        //comment   
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
