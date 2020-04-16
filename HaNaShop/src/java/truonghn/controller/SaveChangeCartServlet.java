/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import truonghn.itemStatus.ItemStatusDAO;
import truonghn.itemStorage.ItemStorageDAO;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
@WebServlet(name = "SaveChangeCartServlet", urlPatterns = {"/SaveChangeCartServlet"})
public class SaveChangeCartServlet extends HttpServlet {
    private final String VIEW_ITEM_IN_CART_SERVLET = "ViewItemsInCartServlet";
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
        String itemList[] = request.getParameterValues("listCodeAndAmount");
        String url = VIEW_ITEM_IN_CART_SERVLET;
        try {
            HttpSession session = request.getSession(false);
            String userID = (String)session.getAttribute("EMAIL");
            //get StatusID===============================================
            ItemStatusDAO staDao = new ItemStatusDAO();
            String statusID = staDao.getStatusID(status);
            //get itemID and amount from parameter=======================
            for (String itemObj : itemList) {
                String item[] = itemObj.split(";;;;;");
                String itemID = item[0].trim();
                String itemName = item[1].trim();
                int amount = Integer.parseInt(item[2].trim());
                
            //check itemID is active======================================    
                ItemStorageDAO stoDao = new ItemStorageDAO();
                boolean isActive = stoDao.checkStatus(itemID, statusID);
                CartDAO cartDao = new CartDAO();
                if(isActive){
                    cartDao.updateCart(userID, itemID, amount);
                }
                else{
                    cartDao.deleteCart(userID, itemID);
                    if(invalid_item_List == null){
                        invalid_item_List = new ArrayList<>();
                    }
                    invalid_item_List.add(itemName);
                }
            }
            request.setAttribute("REMOVELIST", invalid_item_List);
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
