/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mailValidCode.MailError;
import truonghn.haNaUser.HaNaUserDAO;
import truonghn.haNaUserStatus.HaNaUserStatusDAO;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
@WebServlet(name = "VerifyServlet", urlPatterns = {"/VerifyServlet"})
public class VerifyServlet extends HttpServlet {
    private final String HOME_PAGE_SERVLET = "HomePageServlet";
    private final String ERROR_PAGE = "mailCheck.jsp";
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
        PrintWriter out = response.getWriter();
        String verifyCode = request.getParameter("txtVerifyCode").trim();
        String confirmVerifyCode = request.getParameter("txtVerify").trim();
        String password = request.getParameter("txtPassword");
        String mail = request.getParameter("txtMail");
        String name = request.getParameter("txtName");
        String url = ERROR_PAGE;
        boolean FoundErr=false;
        try {
            if(verifyCode.matches(confirmVerifyCode)){
                HaNaUserStatusDAO stdao = new HaNaUserStatusDAO();
                HaNaUserDAO dao = new HaNaUserDAO();
                dao.activeUserStatus(mail,stdao.getStatusID("Active"));
                Cookie cookie = new Cookie(Utils.encodeMail(mail), password);
                cookie.setMaxAge(60 * 60);
                response.addCookie(cookie);
                HttpSession session = request.getSession();
                session.setAttribute("NAME", name);
                session.setAttribute("EMAIL", mail);
                url = HOME_PAGE_SERVLET;
            }else{
                FoundErr = true;
                MailError error = new MailError();
                error.setInvalidVerifyCode("Invaid verify Code!");
                request.setAttribute("ERROR", error);
                request.setAttribute("VERIFYCODE", verifyCode);
                request.setAttribute("PASSWORD", password);
                request.setAttribute("EMAIL", mail);
                request.setAttribute("NAME", name);
            }
        }catch(NamingException e){
            Utils.myBlogFile(e.toString());
        }catch(SQLException e){  
            Utils.myBlogFile(e.toString());
        }finally{
            if(FoundErr){
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
                out.close();
            }else{
                response.sendRedirect(url);
                out.close();
            }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
