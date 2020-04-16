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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import truonghn.haNaUser.HaNaUserDAO;
import truonghn.haNaUser.SignUpCheckError;
import truonghn.haNaUserRole.HaNaUserRoleDAO;
import truonghn.haNaUserStatus.HaNaUserStatusDAO;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
@WebServlet(name = "SignUpServlet", urlPatterns = {"/SignUpServlet"})
public class SignUpServlet extends HttpServlet {
    private final String ERROR_PAGE = "signUp.jsp";
    private final String VERIFY_PAGE = "mailCheck.jsp";
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
        
        HaNaUserDAO dao=null;
        String emailformat = "[a-z0-9A-Z\\_\\-]{1,30}[@][a-z0-9A-Z]{1,30}[.][a-z]{1,10}([.][a-z]{1,10})?";                                                                                               String format = "[0-9a-zA-Z\\s]{1,30}";
        String passformat="[0-9a-zA-Z\\s]{1,30}";
        String nameformat="[0-9a-zA-Z\\s]{1,30}";
        boolean foundErr = false;
        SignUpCheckError errors = new SignUpCheckError();
        String url = ERROR_PAGE;
        try {
            String name = request.getParameter("txtUserName").trim();
            String email = request.getParameter("txtEmail").trim();
            String password = request.getParameter("txtPassword").trim();
            String confirm = request.getParameter("txtConfirm").trim();
            String role = request.getParameter("txtRole").trim();
            String status = request.getParameter("txtStatus").trim();
            if(name.length()<=0 || name.length() > 30){
                foundErr = true;
                errors.setNameLengthErr("Name length requires from 1 to 30 chars!!!");
            }else if(name.matches(nameformat)==false){
                foundErr = true;
                errors.setNameFormatErr("Name contain no special chars!!!");
            }
            
            if(email.length()<=0 || email.length() > 254){
                foundErr = true;
                errors.setEmailLengthErr("Email requires!!!");
            }else if(email.matches(emailformat)==false){
                foundErr = true;
                errors.setEmailFormatErr("Invalid Email format!!!");
            }
            
            if (password.length() <= 0 || password.length() > 30){
                foundErr = true;
                errors.setPasswordFormatErr("Password length requires from 1 to 30 chars!!!");
            }else if(password.matches(passformat)==false){
                foundErr =true;
                errors.setPasswordFormatErr("Password contain no special chars!!!");
            }else if(password.equals(confirm)==false){
                foundErr =true;
                errors.setPasswordFormatErr("Confirm must match password!!!");
            }
            
            dao = new HaNaUserDAO();
            if(dao.checkExisted(email)){
                foundErr = true;
                errors.setEmailExistedErr("This email is existed!!!");
            }
            
            if(foundErr){
                request.setAttribute("CREATEERRORS", errors);
            }else{
                url = VERIFY_PAGE;
                HaNaUserStatusDAO stdao = new HaNaUserStatusDAO();
                String statusID = stdao.getStatusID(status);
                HaNaUserRoleDAO rldao = new HaNaUserRoleDAO();
                String roleID = rldao.getRoleID(role);
                dao.createNewAccount(email, name, password, roleID, statusID);
                
                    //send email and keep verifycode and password.
                    String verifyCode = Utils.activeAcountCode(email);
                        request.setAttribute("NAME", name);
                        request.setAttribute("VERIFYCODE", verifyCode);
                        request.setAttribute("PASSWORD", password);
                        request.setAttribute("EMAIL", email);
            }
        }catch(NamingException e){
            Utils.myBlogFile(e.toString());
        }catch(NoSuchAlgorithmException e){
            Utils.myBlogFile(e.toString());
        }catch(SQLException e){
            Utils.myBlogFile(e.toString());
        }catch(UnsupportedEncodingException e){
            Utils.myBlogFile(e.toString());
        }
        finally{
                RequestDispatcher rd = request.getRequestDispatcher(url);
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
