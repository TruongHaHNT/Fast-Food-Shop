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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import truonghn.haNaUser.HaNaUserDAO;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
@WebServlet(name = "CustomerCartHandllerServlet", urlPatterns = {"/CustomerCartHandllerServlet"})
public class CustomerCartHandllerServlet extends HttpServlet {
    private final String  SAVE_CHANGE_CART_SERVLET = "SaveChangeCartServlet";
    private final String  DELETE_ITEMS_CART_SERVLET = "RemoveItemCartServlet";
    private final String  PURCHASE_ITEM_CART_SERVLET = "PurchaseServlet";
    private final String VIEW_ITEM_SERVLET = "ViewItemServlet";
    private final String LOGIN_PAGE = "login.html";
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
        String userID = "";
        String url = LOGIN_PAGE;
        String button = request.getParameter("btAction");
        try {
            HttpSession session = request.getSession(false);
//            check is user=======================================================
            if(session != null){
                userID = (String)session.getAttribute("EMAIL");
                HaNaUserDAO userDao = new HaNaUserDAO();
                if(userDao.checkExisted(userID)){
                    if(userDao.isActive(userID)){
                        if(button.equals("Save")){
                            url = SAVE_CHANGE_CART_SERVLET;
                        }
                        else if(button.equals("Confirm delete")){
                            url = DELETE_ITEMS_CART_SERVLET;
                        }
                        else if(button.equals("Confirm purchase")){
                            url = PURCHASE_ITEM_CART_SERVLET;
                        }
                        else if(button.equals("View this product in store")){
                            url = VIEW_ITEM_SERVLET;
                        }
                    }
                }
            }
        }catch(NamingException e){
            Utils.myBlogFile(e.toString());
        }catch(SQLException e){
            Utils.myBlogFile(e.toString());
        }finally{
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
