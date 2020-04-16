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
import truonghn.cart.CartDAO;
import truonghn.cart.CartDTO;
import truonghn.haNaUser.HaNaUserDAO;
import truonghn.itemStatus.ItemStatusDAO;
import truonghn.itemStorage.ItemStorageDAO;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {
    private final String SHOW_PRODUCT_SERVLET = "ShowProductServlet";
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
        
        String itemID = request.getParameter("txtItemID");
        String attachitemID[] = request.getParameterValues("cbItemID");
        String txtAmount = request.getParameter("txtAmount");
        int amount = txtAmount != null ? Integer.parseInt(txtAmount):1;
        String userID = "";
        String status = "Active";
        String url = LOGIN_PAGE;
        boolean isActive = false;
        try {
            HttpSession session = request.getSession(false);
//            check is user=======================================================
            if(session != null){
                userID = (String)session.getAttribute("EMAIL");
                HaNaUserDAO userDao = new HaNaUserDAO();
                if(userDao.checkExisted(userID)){
                    if(userDao.isActive(userID)){
                        ItemStatusDAO stDao = new ItemStatusDAO();
                        String statusID = stDao.getStatusID(status);
                        ItemStorageDAO dao = new ItemStorageDAO();
                        isActive = dao.checkStatus(itemID, statusID);
            //            check item is active===============================================
                        if(isActive){
                            if(attachitemID != null){
                                addRecomendedItem(attachitemID, status, userID);
                            }
                            CartDAO Cdao = new CartDAO();
                            CartDTO dto = Cdao.getItemFromCart(userID, itemID);

                            if(dto != null){
                                amount = dto.getAmount()+amount;
                                Cdao.updateCart(userID, itemID, amount);
                            }else{
                                Cdao.addToCart(userID, itemID, amount);
                            }
                            request.setAttribute("NOTIFICATIONS_ADDTOCART", "Item was successfully added to your cart.");
                        }
                        else{
                            request.setAttribute("NOTIFICATIONS_ADDTOCART", "!!!-This item is not available at this time-!!!");
                        }   
                        url = SHOW_PRODUCT_SERVLET;
                    }
                }
            }
            
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

    private void addRecomendedItem(String itemList[], String status, String userID) throws SQLException, NamingException{
        ItemStatusDAO stDao = new ItemStatusDAO();
        String statusID = stDao.getStatusID(status);
        ItemStorageDAO dao = new ItemStorageDAO();
        int amount = 1;
        boolean isActive = false;
        for (String string : itemList) {
            isActive = dao.checkStatus(string, statusID);
            if(isActive){
                CartDAO Cdao = new CartDAO();
                CartDTO dto = Cdao.getItemFromCart(userID, string);

                if(dto != null){
                    amount = dto.getAmount()+amount;
                    Cdao.updateCart(userID, string, amount);
                }else{
                    Cdao.addToCart(userID, string, amount);
                }
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
