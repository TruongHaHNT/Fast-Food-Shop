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
@WebServlet(name = "CheckIsManagerServlet", urlPatterns = {"/CheckIsManagerServlet"})
public class CheckIsManagerServlet extends HttpServlet {
    private final String CREATE_SERVLET = "CreatePageServlet";
    private final String SEARCH_AND_UPDATE_SERVLET = "ManagerItemsListServlet";
    private final String CREATE_NEW_SERVLET = "CheckCreateServlet";
    private final String RETURN_TO_MANAGER_SERVLET = "WelcomeManagerServlet";
    private final String CHANGE_STATUS_ITEM_SERVLET = "ChangeStatusItemServlet";
    private final String MANAGER_VIEW_ITEM_SERVLET = "ManagerViewItemServlet";
    private final String MANAGER_UPDATE_SERVLET = "ManagementUpdateServlet";
    private final String MANAGER_CATEGORY_SERVLET = "ManagementCategoryServlet";
    private final String MANAGER_UPDATE_CATEGORY_SERVLET = "ManagementCreateNewCategoryServlet";
    private final String MANAGER_DELETE_CATEGORY_SERVLET = "ManagementDeleteCategoryServlet";
    private final String MANAGER_USER_SERVLET = "ManagementUserServlet";
    private final String MANAGER_USER_HISTORY_SERVLET = "ManagementUserHistoryServlet";
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
                        if(userDao.isAdmin(userID)){
                            if(button.equals("Create Products")){
                                url = CREATE_SERVLET;
                            }
                            else if(button.equals("View and Update Products") 
                                || button.equals("Return to Products Management Page")){
                                url = SEARCH_AND_UPDATE_SERVLET;
                            }
                            else if(button.equals("Create new")){
                                url = CREATE_NEW_SERVLET;
                            }
                            else if(button.equals("Return to manager Page")){
                                url = RETURN_TO_MANAGER_SERVLET;
                            }
                            else if(button.equals("Confirm delete") 
                                || button.equals("Delete this Product")
                                || button.equals("Confirm reactivate")
                                || button.equals("Reactivate this Product")){
                                url = CHANGE_STATUS_ITEM_SERVLET;
                            }
                            else if(button.equals("detail")
                                || button.equals("View this Product")){
                                url = MANAGER_VIEW_ITEM_SERVLET;
                            }
                            else if(button.equals("Confirm update")){
                                url = MANAGER_UPDATE_SERVLET;
                            }
                            else if(button.equals("Categories management")){
                                url = MANAGER_CATEGORY_SERVLET;
                            }
                            else if(button.equals("Save Change")){
                                url = MANAGER_UPDATE_CATEGORY_SERVLET;
                            }
                            else if(button.equals("Delete Categories")){
                                url = MANAGER_DELETE_CATEGORY_SERVLET;
                            }
                            else if(button.equals("Customers management")){
                                url = MANAGER_USER_SERVLET;
                            }
                            else if(button.equals("View User History")){
                                url = MANAGER_USER_HISTORY_SERVLET;
                            }
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
