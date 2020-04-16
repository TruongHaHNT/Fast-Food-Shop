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
import truonghn.itemCategory.ItemCategoryDAO;
import truonghn.itemCategory.ItemCategoryDTO;
import truonghn.itemStorage.ItemStorageDAO;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
@WebServlet(name = "ManagementCategoryServlet", urlPatterns = {"/ManagementCategoryServlet"})
public class ManagementCategoryServlet extends HttpServlet {
    private final String MANAGER_CATEGORY_PAGE = "categoryManagement.jsp";
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
        String url = MANAGER_CATEGORY_PAGE;
        LinkedHashMap<ItemCategoryDTO,Integer> list = null;
        try{
           
            request.setAttribute("LOGO", Utils.getimage("logo"));
            request.setAttribute("BANNER", Utils.getimage("banner"));
            request.setAttribute("GOOGLE", Utils.getimage("google"));
            request.setAttribute("FACEBOOK", Utils.getimage("facebook"));
            request.setAttribute("TWITTER", Utils.getimage("twitter"));
            request.setAttribute("GMAIL", Utils.getimage("gmail"));
            
            ItemCategoryDAO dao = new ItemCategoryDAO();
            List<ItemCategoryDTO> bfList = dao.getCategoryList();
            ItemStorageDAO stoDao = new ItemStorageDAO();
            for (ItemCategoryDTO dto : bfList) {
                int catID = dto.getCategoryID();
                int num = stoDao.countItem_InCategory(catID);
                if(list == null){
                    list = new LinkedHashMap<>();
                }
                list.put(dto, num);
            }
            request.setAttribute("CATEGORYLIST", list);
            
        }catch (NamingException e){
            Utils.myBlogFile(e.toString());
        }catch (SQLException e){
            Utils.myBlogFile(e.toString());
        }
        finally{
            RequestDispatcher rd =  request.getRequestDispatcher(url);
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
