/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.purchaseHistory;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
public class PurchaseHistoryDAO implements Serializable{
    private List<PurchaseHistoryDTO> list = null;

    public List<PurchaseHistoryDTO> getList() {
        return list;
    }

    public void storePurchaseHistory(String userID, String itemID, String image, String name, int amount, float price, Timestamp time) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = Utils.makeConnection();
            
            if(con != null){
                String sql = "insert into purchaseHistory(mail, foodID, image, name, amount, price, date) "
                        + "Values (?,?,?,?,?,?,?)";
                
                stm = con.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, itemID);
                if(image.contains(Utils.path)){
                    int index = image.lastIndexOf("/");
                    image = image.substring(index+1);
                }
                stm.setString(3, image);
                stm.setString(4, name);
                stm.setInt(5, amount);
                stm.setFloat(6, price);
                stm.setTimestamp(7, time);
                int row = stm.executeUpdate();
            }
        } finally {
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
    }

    public void getPurchaseHistoryList(int curPage, int totalProductPerPage, String userID, String searchVar, String minDate, String maxDate) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int buttonPage = totalProductPerPage*(curPage-1)+1;
        int topPage = totalProductPerPage*curPage;
        try{
            con = Utils.makeConnection();
            if(con != null){
                String sql = " SELECT foodID, image, name, price, purDate, amount " +
                                " FROM (SELECT foodID, image, name, price, purchaseHistory.date as purDate, amount, ROW_NUMBER () OVER (ORDER BY purchaseHistory.date desc) AS RowNum " +
                                    " FROM purchaseHistory " +
                                    " WHERE name like ? " +
                                    " and mail = ? " +
                                    " and purchaseHistory.date between ? and ?) as list " +
                                    " WHERE RowNum >= ? and RowNum <= ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%"+ searchVar +"%");
                stm.setString(2, userID);
                stm.setString(3, minDate);
                stm.setString(4, maxDate);
                stm.setInt(5, buttonPage);
                stm.setInt(6, topPage);
                    //end
                rs = stm.executeQuery();
                while (rs.next()) {
                    String foodID =rs.getString(1);
                    String image =rs.getString(2);
                    String name =rs.getString(3);
                    float price = rs.getFloat(4);
                    Timestamp date = rs.getTimestamp(5);
                    String fmtDate = Utils.timeFormat(date);
                    int amount = rs.getInt(6);
                    
                    PurchaseHistoryDTO dto = new PurchaseHistoryDTO(userID, foodID, Utils.path+image, name, amount, price, fmtDate);
                    if(this.list == null){// khoi tao list neu chua co
                        this.list = new ArrayList<>();
                    }
                    this.list.add(dto);
                }
            }
        }finally{
            if(rs != null){
                rs.close();
            }
            if(stm != null ){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
    }
    
    public int totalPage_OrderByDate(int totalProductPerPage, String userID, String searchVar, String minDate, String maxDate) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        float page = 0;
        try{
            con = Utils.makeConnection();
            if(con != null){
                String sql = " SELECT count (id) " +
                                " FROM purchaseHistory " +
                                " WHERE name like ? " +
                                " and mail = ? " +
                                " and purchaseHistory.date between ? and ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%"+ searchVar +"%");
                stm.setString(2, userID);
                stm.setString(3, minDate);
                stm.setString(4, maxDate);
                rs = stm.executeQuery();
                if (rs.next()) {
                    page = (float)rs.getInt(1);
                    if(page>0){
                        return (int)Math.ceil((page/totalProductPerPage));
                    }
                }
            }
        }finally{
            if(rs != null){
                rs.close();
            }
            if(stm != null ){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        return 0;
    }

    public List<String> getRecommened_AlsoBought(String itemID, String statusID) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<String> list= null;
        try{
            con = Utils.makeConnection();
            if(con != null){
                String sql = " select top (?) purchaseHistory.foodID, count(purchaseHistory.foodID) as num "+
                             " from (select mail, date "+
                             " from purchaseHistory "+
                             " where foodID = ?) as USERID, purchaseHistory, ItemStorage "+
                             " where purchaseHistory.mail = USERID.mail "+
                             " and purchaseHistory.foodID != ? "+
                             " and ItemStorage.ID = purchaseHistory.foodID "+
                             " and purchaseHistory.date = USERID.date "+
                             " and [dbo].[ItemStorage].quantity > 0 "+
                             " and ItemStorage.statusID = ? "+
                             " group by purchaseHistory.foodID "+
                             " order by num DESC ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, Utils.recommended);
                stm.setString(2, itemID);
                stm.setString(3, itemID);
                stm.setString(4, statusID);
                    //end
                rs = stm.executeQuery();
                int count = 0;
                while (rs.next()) {
                    if(list == null){
                        list = new ArrayList<>();
                    }
                    count++; 
                    String foodID =rs.getString(1);
                    list.add(foodID);
                    if(count == Utils.recommended){
                        break;
                    }
                }
                return list;
            }
        }finally{
            if(rs != null){
                rs.close();
            }
            if(stm != null ){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        return null;
    }
}
