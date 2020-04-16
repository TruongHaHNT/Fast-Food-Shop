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
import truonghn.itemCategory.ItemCategoryDAO;
import truonghn.itemStatus.ItemStatusDAO;
import truonghn.itemStorage.ItemStorageDAO;
import truonghn.itemStorage.ItemStorageDTO;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
@WebServlet(name = "ManagerItemsListServlet", urlPatterns = {"/ManagerItemsListServlet"})
public class ManagerItemsListServlet extends HttpServlet {
    private final String UPDATE_PAGE_SERVLET = "ManagerProductPageServlet";
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
        String getsearchVar = request.getParameter("txtAdSearch");
        String getcategory = request.getParameter("cbAdCategory");
        String getstatus = request.getParameter("cbAdStatus");
        String getPage = request.getParameter("curAdPage");
        
        String searchVar = getsearchVar==null ? "":getsearchVar;
        String category = getcategory==null ? "empty":(getcategory.isEmpty() ? "empty":getcategory);
        String status = getstatus==null ? "Active":(getstatus.isEmpty() ? "Active":getstatus);
        int curPage = getPage==null ? 1:(getPage.isEmpty() ? 1:Integer.parseInt(getPage));
        
        String url = UPDATE_PAGE_SERVLET;
        try {
            ItemStatusDAO stDao = new ItemStatusDAO();
            String StatusID = stDao.getStatusID(status);
            ItemCategoryDAO catDao = new ItemCategoryDAO();
            int CatID = catDao.getCatID(category);
            ItemStorageDAO stoDao = new ItemStorageDAO();
            stoDao.getProductList(curPage, Utils.totalContentPerPage, CatID, StatusID, searchVar);
            List<ItemStorageDTO> dto = stoDao.getList();
                request.setAttribute("PRODUCTLIST", dto);

                int totalPage = stoDao.totalPageOfProduct_OrderByDate(Utils.totalContentPerPage, CatID, StatusID, searchVar);
                    request.setAttribute("TOTALPAGE_ORDER_BY_DATE", totalPage);
                    request.setAttribute("CUR_PAGE", curPage);
                    
                if(status.equals("Active")){
                    request.setAttribute("ISACTIVE", "yes");
                }
        }catch(NamingException e){
            Utils.myBlogFile(e.toString());
        }catch(SQLException e){
            Utils.myBlogFile(e.toString());
        }finally{
            RequestDispatcher rs = request.getRequestDispatcher(url);
            rs.forward(request, response);
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
