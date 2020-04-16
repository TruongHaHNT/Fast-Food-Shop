/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import truonghn.cart.CartDAO;
import truonghn.itemRecord.ItemRecordDAO;
import truonghn.itemStatus.ItemStatusDAO;
import truonghn.itemStorage.ItemStorageDAO;
import truonghn.itemStorage.ItemStorageDTO;
import truonghn.purchaseHistory.PurchaseHistoryDAO;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
@WebServlet(name = "PurchaseServlet", urlPatterns = {"/PurchaseServlet"})
public class PurchaseServlet extends HttpServlet {
    private final String VIEW_ITEM_IN_CART_SERVLET = "ViewItemsInCartServlet";
    private final String HOME_PAGE_SERVLET = "HomePageServlet";
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
        String status = "Active";
        List<String> invalid_item_List = null;
        List<String> amount_exceed_item_List = null;
        String itemList[] = request.getParameterValues("listCodeAndAmount");
        String url = VIEW_ITEM_IN_CART_SERVLET;
        boolean foundErr = false;
        try {
            HttpSession session = request.getSession(false);
            String userID = (String)session.getAttribute("EMAIL");
            
            ItemStorageDAO stoDao = null;
            PurchaseHistoryDAO purDao = null;
            CartDAO cartDao = null;
            //get StatusID===============================================
            ItemStatusDAO staDao = new ItemStatusDAO();
            String statusID = staDao.getStatusID(status);
            //get itemID and amount from parameter=======================
            //check valid item===========================================
            for (String itemObj : itemList) {
                String item[] = itemObj.split(";;;;;");
                String itemID = item[0].trim();
                String itemName = item[1].trim();
                int amount = Integer.parseInt(item[2].trim());
                
            //check itemID is active======================================    
                stoDao = new ItemStorageDAO();
                boolean isActive = stoDao.checkStatus(itemID, statusID);
                cartDao = new CartDAO();
                if(isActive){
//                    check amount exceeded or not=======================
                    ItemStorageDTO stoDto = stoDao.getIteminfo(itemID);
                    if(stoDto.getQuantity()<amount){
                        if(amount_exceed_item_List == null){
                            amount_exceed_item_List = new ArrayList<>();
                        }
                        amount_exceed_item_List.add(stoDto.getFoodname()+"; "+stoDto.getQuantity()+" left"+" | You've bought "+amount);
                        foundErr = true;
                    }
                }
                else{
                    //remove invalid item.........................
                    cartDao.deleteCart(userID, itemID);
                    if(invalid_item_List == null){
                        invalid_item_List = new ArrayList<>();
                    }
                    invalid_item_List.add(itemName);
                    foundErr = true;
                }
            }
            
            
            if(foundErr){
                request.setAttribute("REMOVELIST", invalid_item_List);
                request.setAttribute("AMOUNTEXCEEDLIST", amount_exceed_item_List);
            }else{
                Timestamp time = Utils.getTime();
                        
                for (String itemObj : itemList) {
                    String item[] = itemObj.split(";;;;;");
                    String itemID = item[0].trim();
                    int amount = Integer.parseInt(item[2].trim());
  
                    stoDao = new ItemStorageDAO();
                    purDao = new PurchaseHistoryDAO();
                    cartDao = new CartDAO();
                    
                    ItemStorageDTO stoDto = stoDao.getIteminfo(itemID);
                    purDao.storePurchaseHistory(userID, itemID, stoDto.getImage(), stoDto.getFoodname(), amount, stoDto.getPrice(), time);
                    int left = stoDto.getQuantity()-amount;
                    stoDao.updatePurchaseProccess(itemID, left, amount);
                    cartDao.deleteCart(userID, itemID);
                    ItemRecordDAO recDAO = new ItemRecordDAO();
                    recDAO.recordItem(itemID);
                }
                request.setAttribute("NOTIFICATION", "Thank you for purchase our product.");
                url = HOME_PAGE_SERVLET;
            }
        }catch(NamingException e){
            Utils.myBlogFile(e.toString());
        }catch (SQLException e){
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
