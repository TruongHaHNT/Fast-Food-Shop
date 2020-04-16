/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import truonghn.purchaseHistory.PurchaseHistoryDAO;
import truonghn.purchaseHistory.PurchaseHistoryDTO;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
@WebServlet(name = "ManagementUserHistoryServlet", urlPatterns = {"/ManagementUserHistoryServlet"})
public class ManagementUserHistoryServlet extends HttpServlet {
    private final String  PURCHASE_HISTORY_PAGE_SERVLET = "userHistoryManagement.jsp";
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
        String userID = request.getParameter("txtAdUserID");
        String searchVar = request.getParameter("txtAdSearchHistory");
        String searchMinTime = request.getParameter("txtAdMinDate");
        String searchMaxTime = request.getParameter("txtAdMaxDate");
        String getPage = request.getParameter("curHistoryPage");
        int page = getPage == null ? 1:(getPage.isEmpty() ? 1:Integer.parseInt(getPage));
        String itemName = searchVar == null ? "":(searchVar);
        String minDateTime = searchMinTime == null ? Utils.minDate:(searchMinTime.isEmpty() ? Utils.minDate:searchMinTime);
        String maxDateTime = searchMaxTime == null ? Utils.maxDate:(searchMaxTime.isEmpty() ? Utils.maxDate:searchMaxTime);
        
        List<PurchaseHistoryDTO> list = null;
        
        String url = PURCHASE_HISTORY_PAGE_SERVLET;
        try {
            request.setAttribute("LOGO", Utils.getimage("logo"));
            request.setAttribute("BANNER", Utils.getimage("banner"));
            request.setAttribute("SEARCHICON", Utils.getimage("searchIcon"));
            request.setAttribute("GOOGLE", Utils.getimage("google"));
            request.setAttribute("FACEBOOK", Utils.getimage("facebook"));
            request.setAttribute("TWITTER", Utils.getimage("twitter"));
            request.setAttribute("GMAIL", Utils.getimage("gmail"));
            request.setAttribute("VIEWICON", Utils.getimage("view"));
            
            minDateTime = minDateTime + " 00:00:00:000";
            maxDateTime = maxDateTime + " 23:59:59:999";

            PurchaseHistoryDAO purDao = new PurchaseHistoryDAO();
            purDao.getPurchaseHistoryList(page, Utils.totalContentPerPage, userID, itemName, minDateTime, maxDateTime);
            list = purDao.getList();
            request.setAttribute("PURCHASEHISTORYLIST", list);

            int totalPage = purDao.totalPage_OrderByDate(Utils.totalContentPerPage, userID, itemName, minDateTime, maxDateTime);
            request.setAttribute("TOTALPAGE_ORDER_BY_DATE_PURHISTORY", totalPage);
            request.setAttribute("CUR_PAGE_PURHISTORY", page); 

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
