/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.controller;

import truonghn.haNaUser.HaNaUserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
@WebServlet(name = "StartUpServlet", urlPatterns = {"/StartUpServlet"})
public class StartUpServlet extends HttpServlet {
    private final String HOME_PAGE_SERVLET = "HomePageServlet";
    private final String MANAGER_PAGE = "WelcomeManagerServlet";
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
        String url = HOME_PAGE_SERVLET;
        try {
            Utils.setCalendar();
            Cookie[] cookies = request.getCookies();
            if(cookies != null){
                for(Cookie cookie : cookies){
                    String email = cookie.getName();
                    String password = cookie.getValue();
                    
                    email = Utils.decodeMail(email);
                    
                    
                    HaNaUserDAO dao = new HaNaUserDAO();
                    boolean result = dao.checkLogin(email, password);                    
                    if(result){
                        if(dao.isActive(email)){
                            cookie.setMaxAge(24*60*60);
                            response.addCookie(cookie);
                            String name = dao.getUserinfo(email, password).getName();
                            HttpSession session = request.getSession();
                            session.setAttribute("NAME", name);
                            session.setAttribute("EMAIL", email);
                            if(dao.isAdmin(email)){
                                    url = MANAGER_PAGE;
                            }        
                        }
                    
                    break;
                    }
                }
            }
        }catch(UnsupportedEncodingException e){
            Utils.myBlogFile(e.toString());
        }catch(NoSuchAlgorithmException e){
            Utils.myBlogFile(e.toString());
        }catch(SQLException e){
            Utils.myBlogFile(e.toString());
        }catch(NamingException e){
            Utils.myBlogFile(e.toString());
        }
        finally{
            RequestDispatcher rd =  request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
