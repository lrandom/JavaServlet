/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lrandom
 */
public class AuthorizationServlet extends HttpServlet {

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
            out.println("<title>Servlet AuthorizationServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AuthorizationServlet at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        response.getWriter().print("Xin chao cac ban Java 14");
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
        //processRequest(request, response);
        //Lấy về giá trị của input username
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        //Lấy về giá trị của input password
        String password = "admin";// request.getParameter("password");
        
          Connection conn = null;
        String urlConnectMySql = 
                "jdbc:mysql://localhost:3306/test";
        try {
        conn = 
                DriverManager.getConnection(
                        urlConnectMySql
                        ,"root"
                        ,"koodinh"); 
        }catch(Exception e){
             response.getWriter().print(e.getMessage());
           e.printStackTrace();
        }
        boolean isExist = false;
        
        try {
            
         response.getWriter().print(password);
         String select = "SELECT * FROM users where username=? AND password = ?";
         PreparedStatement prp = conn.prepareCall(select);
         
          
         byte[] encodePass = password.getBytes("UTF-8");
         MessageDigest md = MessageDigest.getInstance("MD5");
         byte[] theEncode = md.digest(encodePass); 
          response.getWriter().print(theEncode.toString());
        
         prp.setString(1, username);
         
         prp.setString(2, "21232f297a57a5a743894a0e4a801fc3");
         isExist = true;//prp.execute();
        } catch (Exception e) {
             response.getWriter().print(e.getMessage());
        
             e.printStackTrace();
             
        }
        
        if(isExist){
            response.getWriter().write("Dang nhap thanh cong");
        }else{
            response.getWriter().write("Dang nhap that bai");
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
