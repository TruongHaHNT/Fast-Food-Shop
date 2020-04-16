/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.controller;

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
import truonghn.haNaUser.CheckLoginError;
import truonghn.haNaUser.HaNaUserDAO;
import truonghn.haNaUser.HaNaUserDTO;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    private final String HOME_PAGE_SERVLET = "HomePageServlet";
    private final String ADMIN_PAGE_SERVLET = "WelcomeManagerServlet";
    private final String NEW_USER_PAGE = "mailCheck.jsp";
    private final String ERROR_PAGE = "login.jsp";
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
        String emailformat = "[a-z0-9A-Z\\_\\-]{1,30}[@][a-z0-9A-Z]{1,30}[.][a-z]{1,10}([.][a-z]{1,10})?";                                                                                               String format = "[0-9a-zA-Z\\s]{1,30}";
        String passformat="[0-9a-zA-Z\\s]{1,30}";
//        String role = "Member";
//        String status = "Active";
        String url = ERROR_PAGE;
        String email = request.getParameter("txtEmail").trim().toLowerCase();
        String password = request.getParameter("txtPassword").trim();
        boolean newUser = false;
        boolean foundErr = false;
        CheckLoginError errors = new CheckLoginError();
        HaNaUserDAO dao = null;
        try {
            if(email.length()<=0 || email.length() > 254){
                foundErr = true;
                errors.setEmailLengthErr("Email requires!!!");
            }else if(email.matches(emailformat)==false){
                foundErr = true;
                errors.setEmailFormatErr("Invalid Email format!!!");
            }
            
            if (password.length() <= 0 || password.length() > 30){
                foundErr = true;
                errors.setPasswordFormatErr("Password length required from 1 to 30 chars!!!");
            }else if(password.matches(passformat)==false){
                foundErr =true;
                errors.setPasswordFormatErr("Password contain no special chars!!!");
            }
            if(foundErr){
                request.setAttribute("CREATEERRORS", errors);
            }else{
                dao=new HaNaUserDAO();
                boolean exist = dao.checkExisted(email);
                if(exist){
                    boolean result = dao.checkLogin(email, password);
                    if(result){
                        HaNaUserDTO dto = dao.getUserinfo(email, password);
                        String names = dto.getName();
//                        if(dto.getSattus().equalsIgnoreCase(status)){
                        if(dao.isActive(email)){
//                            if(dto.getRole().equalsIgnoreCase(role)){
                            if(dao.isAdmin(email)){
                                url = ADMIN_PAGE_SERVLET;
                            }else{
                                url = HOME_PAGE_SERVLET;
                            }
                            Cookie cookie = new Cookie(Utils.encodeMail(email), password);
                            cookie.setMaxAge(24 * 60 * 60);
                            response.addCookie(cookie);
                            HttpSession session = request.getSession();
                            session.setAttribute("NAME", names);
                            session.setAttribute("EMAIL", email);
                        }
                        else{
                            url = NEW_USER_PAGE;
                            newUser =true;
                            String verifyCode = Utils.activeAcountCode(email);
                            request.setAttribute("VERIFYCODE", verifyCode);
                            request.setAttribute("PASSWORD", password);
                            request.setAttribute("NAME", names);
                            request.setAttribute("EMAIL", email);
                        }
                    }
                    else{
                        foundErr = true;
                        errors.setIncorrectPassword("Incorrect password!!!!");
                        request.setAttribute("CREATEERRORS", errors);
                    }
                }else{
                    foundErr = true;
                    errors.setNotExistErr("This email is not Existed!!!!");
                    request.setAttribute("CREATEERRORS", errors);
                }
            }
        }catch(NamingException e){
            Utils.myBlogFile(e.toString());
        }catch(NoSuchAlgorithmException e){
            Utils.myBlogFile(e.toString());
        }catch(UnsupportedEncodingException e){
            Utils.myBlogFile(e.toString());
        }catch(SQLException e){
            Utils.myBlogFile(e.toString());
        }
        
        finally{
            if(foundErr || newUser){
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
