/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import data.UserData;
import java.beans.Statement;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Session;
import javax.servlet.RequestDispatcher;
/**
 *
 * @author Sheta
 */
public class LoginServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        //getting user data
        String name = "";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        System.out.println(username);
        
        //connecting with derby db
        java.sql.Statement st = null;
        ResultSet rs = null;
        try {
            
            
            st = (java.sql.Statement) getServletContext().getAttribute("Stnt");
            rs = st.executeQuery("SELECT * FROM ROOT.CUSTOMER where username like " + "'" + username +"'" + " and password like "
            + "'" + password + "'" );
            
            
                if(rs.next()){
                    UserData user = new UserData();
                    user.setName(rs.getString("name"));
                    user.setUserName(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    request.getSession().setAttribute("user", user);
                    request.setAttribute("user", user);
                    RequestDispatcher r = request.getRequestDispatcher("UserServlet");
                    r.forward(request, response);
                }else{
                    response.sendRedirect("noUser.html");
                }
                
            }  
         catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
