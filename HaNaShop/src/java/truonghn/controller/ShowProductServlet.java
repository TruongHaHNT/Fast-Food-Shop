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
@WebServlet(name = "ShowProductServlet", urlPatterns = {"/ShowProductServlet"})
public class ShowProductServlet extends HttpServlet {
    private final String PRODUCT_PAGE_SERVLET = "ProductPageServlet";
    private final String ERROR_PAGE = "ProductPageServlet";
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
        PrintWriter out = response.getWriter() ;
        String url = ERROR_PAGE;
        String searchValue = request.getParameter("txtSearch");
        String catValue = request.getParameter("cbCategory");
        String moneyMinValue = request.getParameter("txtMin");
        String moneyMaxValue = request.getParameter("txtMax");
        
        searchValue = searchValue != null ? (searchValue):"";
        catValue = catValue != null ? (catValue.isEmpty() ? "empty":catValue):"empty";
        float min =moneyMinValue != null ? (moneyMinValue.isEmpty() ? 0:Float.parseFloat(moneyMinValue)):0;
        float max =moneyMaxValue != null ? (moneyMaxValue.isEmpty() ? Float.MAX_VALUE:Float.parseFloat(moneyMaxValue)):Float.MAX_VALUE;
        String status = "Active";
        String getPage = request.getParameter("curPage");
        int curPage = getPage==null ? 1:getPage.isEmpty() ? 1:Integer.parseInt(getPage);
        try{
                ItemStatusDAO stDao = new ItemStatusDAO();
                String StatusID = stDao.getStatusID(status);
                ItemCategoryDAO catDao = new ItemCategoryDAO();
                int CatID = catDao.getCatID(catValue);
                ItemStorageDAO dao = new ItemStorageDAO();
                dao.getProductList(curPage, Utils.totalContentPerPage, searchValue, CatID, min, max, StatusID);
                List<ItemStorageDTO> dto = dao.getList();
                request.setAttribute("ITEMLIST", dto);
                
                int totalPage = dao.totalPageOfProduct_OrderByDate(Utils.totalContentPerPage, searchValue, CatID, min, max, StatusID);
                request.setAttribute("TOTALPAGE_ORDER_BY_DATE", totalPage);
                request.setAttribute("CUR_PAGE", curPage);
                url = PRODUCT_PAGE_SERVLET;
        }catch(NamingException e){
            Utils.myBlogFile(e.toString());
        }catch(SQLException e){
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
