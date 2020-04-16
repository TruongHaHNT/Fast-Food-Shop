/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedHashMap;
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
import truonghn.cart.CartDTO;
import truonghn.haNaUser.HaNaUserDAO;
import truonghn.itemStorage.ItemStorageDAO;
import truonghn.itemStorage.ItemStorageDTO;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
@WebServlet(name = "ViewItemsInCartServlet", urlPatterns = {"/ViewItemsInCartServlet"})
public class ViewItemsInCartServlet extends HttpServlet {
    private final String CART_PAGE_SERVLET = "CartPageServlet";
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
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        LinkedHashMap<CartDTO, ItemStorageDTO> list = null;
        String userID = "";
        String url = LOGIN_PAGE;
        try {
            HttpSession session = request.getSession(false);
//            check is user=======================================================
            if(session != null){
                userID = (String)session.getAttribute("EMAIL");
                HaNaUserDAO userDao = new HaNaUserDAO();
                if(userDao.checkExisted(userID)){
                    if(userDao.isActive(userID)){
                        CartDAO cartDao = new CartDAO();
                        cartDao.getCartList(userID);
                        List<CartDTO> cartDto = cartDao.getList();
                        if(cartDto != null){
                            list = new LinkedHashMap<>();
                            ItemStorageDAO dao = new ItemStorageDAO();

                            for (CartDTO cartDTO : cartDto) {
                                String itemID = cartDTO.getItemID();
                                ItemStorageDTO dto = dao.getIteminfo(itemID);
                                list.put(cartDTO, dto);
                            }
                            request.setAttribute("CARTLIST", list);
                        }
                        url = CART_PAGE_SERVLET;
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
