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
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import truonghn.itemCategory.ItemCategoryDAO;
import truonghn.itemRecord.ItemRecordDAO;
import truonghn.itemStatus.ItemStatusDAO;
import truonghn.itemStorage.CreateNewError;
import truonghn.itemStorage.ItemStorageDAO;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
@WebServlet(name = "CheckCreateServlet", urlPatterns = {"/CheckCreateServlet"})
public class CheckCreateServlet extends HttpServlet {
    private final String CREATE_SERVLET = "CreateServlet";
    private final String ERROR_PAGE = "CreatePageServlet";
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
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        String url = ERROR_PAGE;
        
        String itemId =request.getParameter("txtID").trim();
        String itemName =request.getParameter("txtName").trim();
        String priceSt =request.getParameter("txtPrice").trim();
        String ExCategory =request.getParameter("cbCategory").trim();
        String quantitySt =request.getParameter("txtQuantity").trim();
        String description =request.getParameter("txtdescription").trim();
        Part part = request.getPart("fileImage");
        String fileName = Utils.extractFileName(part);
        
        float price = Float.parseFloat(priceSt);
        int quantity = Integer.parseInt(quantitySt);
        String checkPath = (".*.jpg$"
                        + "||.*.JPG$"
                        + "||.*.png$"
                        + "||.*.PNG$"
                        + "||.*.GIF$"
                        + "||.*.gif$");        
        try {
        CreateNewError error = new CreateNewError();
        ItemStorageDAO dao = new ItemStorageDAO();
        ItemCategoryDAO catDao = new ItemCategoryDAO();
        boolean foundErr = false;
        
        if(itemId.length()<1 || itemId.length()>20){
            foundErr = true;
            error.setItemIDLengthError("Food/Drink ID must be between 1 and 20 characters!");
        }else if(dao.checkExistedID(itemId)){
            foundErr = true;
            error.setItemIDExisted("Food/Drink ID existed!");
        }

        if(itemName.length()<1 || itemName.length()>50){
            foundErr = true;
            error.setItemNameLengthError("Food/Drink Name must be between 1 and 20 characters!");
        }

        if(ExCategory.equals("empty")){
            foundErr = true;
            error.setItemCategoryError("Food/Drink category required!");
        }
        

        if(description.length()<1){
            foundErr = true;
            error.setItemDescriptionLengthError("Food/Drink Description required!");
        }

        if(fileName.isEmpty()){
            foundErr = true;
            error.setImagePathError("Food/Drink image required!");
        }else if(!fileName.matches(checkPath)){
            foundErr = true;
            error.setImagePathError("Only support .jpg or .gif or .png!");
        }
        
        if(foundErr){
            request.setAttribute("CREATEERRORS", error);
        }else{
            
            Timestamp date = Utils.getTime();
            int CatID = catDao.getCatID(ExCategory);
            ItemStatusDAO staDao = new ItemStatusDAO();
            String statusID = staDao.getStatusID("Active");
            dao.createNewItem(itemId, itemName, fileName, description, price, date, CatID, quantity, statusID, 0);
            
            ItemRecordDAO recDao = new ItemRecordDAO();
            recDao.createRecord(itemId);
            
            url = CREATE_SERVLET;
        }
        
        }catch(NamingException e){
            Utils.myBlogFile(e.toString());
            e.printStackTrace();
        }catch(SQLException e){
            Utils.myBlogFile(e.toString());
            e.printStackTrace();
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
