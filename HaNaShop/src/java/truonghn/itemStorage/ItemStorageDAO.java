/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.itemStorage;

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

public class ItemStorageDAO implements Serializable{
    private List<ItemStorageDTO> list = null;

    public List<ItemStorageDTO> getList() {
        return list;
    }
    
    public void getProductList(int curPage, int totalProductPerPage, String searchVar, int categoryID, float minMoney, float maxMoney, String StatusID) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int buttonPage = totalProductPerPage*(curPage-1)+1;
        int topPage = totalProductPerPage*curPage;
        try{
            con = Utils.makeConnection();
            if(con != null){
                String sql = " declare "+
                                " @num int = ?; "+//1
                            " IF @num = 0 "+
                                " BEGIN "+
                                    " SELECT ID, foodName, image, price, Date, categoryID, quantity, sold "+
                                    " FROM (SELECT ID, foodName, image, price, Date, categoryID, quantity, sold, ROW_NUMBER () OVER (ORDER BY Date desc) AS RowNum "+
                                            " FROM ItemStorage "+
                                            " WHERE foodName like ? "+//2
                                            " and quantity > 0 "+
                                            " and statusID = ? "+//3
                                            " and price >= ? "+//4
                                            " and price <= ?) as list " +//5
                                    "WHERE RowNum >= ? and RowNum <= ? "+//6,7
                                " END "+
                            " ELSE "+
                                " BEGIN "+
                                    " SELECT ID, foodName, image, price, Date, categoryID, quantity, sold "+
                                    " FROM (SELECT ID, foodName, image, price, Date, categoryID, quantity, sold, ROW_NUMBER () OVER (ORDER BY Date desc) AS RowNum "+
                                            " FROM ItemStorage "+
                                            " WHERE foodName like ? "+//8
                                            " and categoryID = @num "+
                                            " and quantity > 0 "+
                                            " and statusID = ? "+//9
                                            " and price >= ? "+///10
                                            " and price <= ?) as list "+//11
                                    " WHERE RowNum >= ? and RowNum <= ? "+//12,13
                            " END; ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, categoryID);
                //selecting products in all category.
                stm.setString(2, "%"+searchVar+"%");
                stm.setString(3, StatusID);
                stm.setFloat(4, minMoney);
                stm.setFloat(5, maxMoney);
                stm.setInt(6, buttonPage);
                stm.setInt(7, topPage);
                    //end
                //selecting products in specify category.
                stm.setString(8, "%"+searchVar+"%");
                stm.setString(9, StatusID);
                stm.setFloat(10, minMoney);
                stm.setFloat(11, maxMoney);
                stm.setInt(12, buttonPage);
                stm.setInt(13, topPage);
                    //end
                rs = stm.executeQuery();
                while (rs.next()) {

                    String id = rs.getString(1); 
                    String foodname = rs.getString(2);
                    String image = rs.getString(3);
                    float price = rs.getFloat(4);
                    Timestamp date = rs.getTimestamp(5);
                    int catID = rs.getInt(6);
                    int quantity = rs.getInt(7);
                    int sold = rs.getInt(8);

                    ItemStorageDTO dto = new ItemStorageDTO(id, foodname, Utils.path+image, price, Utils.timeFormat(date), catID, quantity, sold);
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
    
    public int totalPageOfProduct_OrderByDate(int totalContentPerPage, String searchVar, int categoryID, float minMoney, float maxMoney, String StatusID) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        float page = 0;
        try{
            con = Utils.makeConnection();
            if(con != null){
                String sql = " declare "+
                                " @num int = ?; "+//1
                            " IF @num = 0 "+
                                " BEGIN "+
                                    " SELECT count (ID) "+
                                            " FROM ItemStorage "+
                                            " WHERE foodName like ? "+//2
                                            " and quantity > 0 "+
                                            " and statusID = ? "+//3
                                            " and price >= ? "+//4
                                            " and price <= ? " +//5
                                " END "+
                            " ELSE "+
                                " BEGIN "+
                                    " SELECT count (ID) "+
                                            " FROM ItemStorage "+
                                            " WHERE foodName like ? "+//6
                                            " and categoryID = @num "+
                                            " and quantity > 0 "+
                                            " and statusID = ? "+//7
                                            " and price >= ? "+///8
                                            " and price <= ? "+//9
                            " END; ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, categoryID);
                //selecting products in all category.
                stm.setString(2, "%"+searchVar+"%");
                stm.setString(3, StatusID);
                stm.setFloat(4, minMoney);
                stm.setFloat(5, maxMoney);
                    //end
                //selecting products in specify category.
                stm.setString(6, "%"+searchVar+"%");
                stm.setString(7, StatusID);
                stm.setFloat(8, minMoney);
                stm.setFloat(9, maxMoney);
                    //end
                rs = stm.executeQuery();
                if (rs.next()) {
                    page = (float)rs.getInt(1);
                    if(page>0){
                        return (int)Math.ceil((page/totalContentPerPage));
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
    
    public void createNewItem(String ID, String name, String image, String description, float price, Timestamp date, int catID, int quantity, String statusID, int sold) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = Utils.makeConnection();
            
            if(con != null){
                String sql = "insert into ItemStorage(ID, foodName, image, description, price, Date, categoryID, quantity, statusID, sold) "
                        + "Values (?,?,?,?,?,?,?,?,?,?)";
                
                stm = con.prepareStatement(sql);
                stm.setString(1, ID);
                stm.setString(2, name);
                stm.setString(3, image);
                stm.setString(4, description);
                stm.setFloat(5, price);
                stm.setTimestamp(6, date);
                stm.setInt(7, catID);
                stm.setInt(8, quantity);
                stm.setString(9, statusID);
                stm.setInt(10, sold);
                
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
    
    public boolean checkExistedID(String id) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = Utils.makeConnection();
            if(con !=null){
                String sql = "select ID"
                        + " from ItemStorage"
                        + " where ID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                
                rs = stm.executeQuery();
                
                if(rs.next()){
                    return true;
                }
            }
        } finally{
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
        return false;
    }

    public ItemStorageDTO getItemInfo(String itemID, String statusID) throws SQLException, NamingException {
        Connection con = null;//1
        PreparedStatement stm = null;//3
        ResultSet rs = null;//4
        try {//2
            con = Utils.makeConnection();
            if( con != null){//3
                String sql ="SELECT ID, foodName, image, price, description, Date, categoryID, quantity, sold "+
                            " FROM ItemStorage "+
                            " WHERE ID = ? "+ 
                            " and statusID = ? "+
                            " and quantity > 0 ";
                stm = con.prepareStatement(sql);
                stm.setString(1, itemID);
                stm.setString(2, statusID);
                rs = stm.executeQuery();
                if(rs.next()){
                    String id = rs.getString(1); 
                    String foodname = rs.getString(2);
                    String image = rs.getString(3);
                    float price = rs.getFloat(4);
                    String descriotion = rs.getString(5);
                    Timestamp date = rs.getTimestamp(6);
                    int catID = rs.getInt(7);
                    int quantity = rs.getInt(8);
                    int sold = rs.getInt(9);
                    ItemStorageDTO dto = new ItemStorageDTO(id, foodname, Utils.path+image, descriotion, price, Utils.timeFormat(date), catID, quantity, statusID, sold);
                    return dto;
                }
            }//end if con
        } finally {
            if(rs != null){
                rs.close();
            }
            if( stm != null){
                stm.close();
            }
            if( con != null){
                con.close();
            }
        }
        return null;
    }

    public boolean checkStatus(String itemID, String statusID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = Utils.makeConnection();
            if(con !=null){
                String sql = " select ID "
                        + " from ItemStorage "
                        + " where ID = ? "
                        + " and statusID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, itemID);
                stm.setString(2, statusID);
                
                rs = stm.executeQuery();
                
                if(rs.next()){
                    return true;
                }
            }
        } finally{
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
        return false;
    }

    public ItemStorageDTO getIteminfo(String itemID) throws SQLException, NamingException {
        Connection con = null;//1
        PreparedStatement stm = null;//3
        ResultSet rs = null;//4
        try {//2
            con = Utils.makeConnection();
            if( con != null){//3
                String sql ="SELECT ID, foodName, image, price, Date, description, categoryID, quantity, ItemStatus.status, sold "+
                            " FROM ItemStorage, ItemStatus "+
                            " WHERE ID = ? "+ 
                            " and ItemStorage.statusID = ItemStatus.statusID " +
                            " and quantity > 0 "; 
                stm = con.prepareStatement(sql);
                stm.setString(1, itemID);
                rs = stm.executeQuery();
                if(rs.next()){
                    String id = rs.getString(1); 
                    String foodname = rs.getString(2);
                    String image = rs.getString(3);
                    float price = rs.getFloat(4);
                    Timestamp date = rs.getTimestamp(5);
                    String des = rs.getString(6);
                    int catID = rs.getInt(7);
                    int quantity = rs.getInt(8);
                    String status = rs.getString(9);
                    int sold = rs.getInt(10);
                    ItemStorageDTO dto = new ItemStorageDTO(id, foodname, Utils.path+image,des, price, Utils.timeFormat(date), catID, quantity, status, sold);
                    return dto;
                }
            }//end if con
        } finally {
            if(rs != null){
                rs.close();
            }
            if( stm != null){
                stm.close();
            }
            if( con != null){
                con.close();
            }
        }
        return null;
    }

    public void updatePurchaseProccess(String itemID, int left, int amount) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = Utils.makeConnection();
            
            if(con != null){
                String sql ="update ItemStorage set quantity = ?, sold = sold + ? "
                            + " where ID = ? ";
                
                stm = con.prepareStatement(sql);
                stm.setInt(1, left);
                stm.setInt(2, amount);
                stm.setString(3, itemID);
                
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

    public void getProductList(int curPage, Integer totalContentPerPage, int CatID, String StatusID, String searchVar) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int buttonPage = totalContentPerPage*(curPage-1)+1;
        int topPage = totalContentPerPage*curPage;
        try{
            con = Utils.makeConnection();
            if(con != null){
                String sql = " declare "+
                                " @num int = ?; "+//1
                            " IF @num = 0 "+
                                " BEGIN "+
                                    " SELECT ID, foodName, image, price, Date, categoryID, quantity, sold "+
                                    " FROM (SELECT ID, foodName, image, price, Date, categoryID, quantity, sold, ROW_NUMBER () OVER (ORDER BY Date desc) AS RowNum "+
                                            " FROM ItemStorage "+
                                            " WHERE foodName like ? "+//2
                                            " and statusID = ?) as list " +//3
                                    "WHERE RowNum >= ? and RowNum <= ? "+//4,5
                                " END "+
                            " ELSE "+
                                " BEGIN "+
                                    " SELECT ID, foodName, image, price, Date, categoryID, quantity, sold "+
                                    " FROM (SELECT ID, foodName, image, price, Date, categoryID, quantity, sold, ROW_NUMBER () OVER (ORDER BY Date desc) AS RowNum "+
                                            " FROM ItemStorage "+
                                            " WHERE foodName like ? "+//6
                                            " and categoryID = @num "+
                                            " and statusID = ?) as list "+//7
                                    " WHERE RowNum >= ? and RowNum <= ? "+//8,9
                            " END; ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, CatID);
                //selecting products in all category.
                stm.setString(2, "%"+searchVar+"%");
                stm.setString(3, StatusID);
                stm.setInt(4, buttonPage);
                stm.setInt(5, topPage);
                    //end
                //selecting products in specify category.
                stm.setString(6, "%"+searchVar+"%");
                stm.setString(7, StatusID);
                stm.setInt(8, buttonPage);
                stm.setInt(9, topPage);
                    //end
                rs = stm.executeQuery();
                while (rs.next()) {

                    String id = rs.getString(1); 
                    String foodname = rs.getString(2);
                    String image = rs.getString(3);
                    float price = rs.getFloat(4);
                    Timestamp date = rs.getTimestamp(5);
                    int catID = rs.getInt(6);
                    int quantity = rs.getInt(7);
                    int sold = rs.getInt(8);

                    ItemStorageDTO dto = new ItemStorageDTO(id, foodname, Utils.path+image, price, Utils.timeFormat(date), catID, quantity, sold);
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

    public int totalPageOfProduct_OrderByDate(Integer totalContentPerPage, int CatID, String StatusID, String searchVar) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        float page = 0;
        try{
            con = Utils.makeConnection();
            if(con != null){
                String sql = " declare "+
                                " @num int = ?; "+//1
                            " IF @num = 0 "+
                                " BEGIN "+
                                    " SELECT count (ID) "+
                                            " FROM ItemStorage "+
                                            " WHERE foodName like ? "+//2
                                            " and statusID = ? "+//3
                                " END "+
                            " ELSE "+
                                " BEGIN "+
                                    " SELECT count (ID) "+
                                            " FROM ItemStorage "+
                                            " WHERE foodName like ? "+//4
                                            " and categoryID = @num "+
                                            " and statusID = ? "+//5
                            " END; ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, CatID);
                //selecting products in all category.
                stm.setString(2, "%"+searchVar+"%");
                stm.setString(3, StatusID);
                    //end
                //selecting products in specify category.
                stm.setString(4, "%"+searchVar+"%");
                stm.setString(5, StatusID);
                    //end
                rs = stm.executeQuery();
                if (rs.next()) {
                    page = (float)rs.getInt(1);
                    if(page>0){
                        return (int)Math.ceil((page/totalContentPerPage));
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

    public void changeItemStatus(String itemID, String StatusID, Timestamp date) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try{
            con = Utils.makeConnection();
            if(con != null){
                String sql = "update ItemStorage set statusID = ?, Date = ? "
                                + " where ID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, StatusID);
                stm.setTimestamp(2, date);
                stm.setString(3, itemID);
                int row = stm.executeUpdate();
            }
        }finally{
            if(con!=null){
                con.close();
            }
            if(stm!=null){
                stm.close();
            }
        }
    }

    public void updateItemDetail(String itemId, String itemName, float price, int quantity, int CatID, String description, String fileName, Timestamp date) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try{
            con = Utils.makeConnection();
            if(con != null){
                String sql = "update ItemStorage set foodName = ?, image = ?, price = ?, Date = ?, categoryID = ?, quantity = ?, description = ? "
                                + " where ID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, itemName);
                stm.setString(2, fileName);
                stm.setFloat(3, price);
                stm.setTimestamp(4, date);
                stm.setInt(5, CatID);
                stm.setInt(6, quantity);
                stm.setString(7, description);
                stm.setString(8, itemId);
                int row = stm.executeUpdate();
            }
        }finally{
            if(con!=null){
                con.close();
            }
            if(stm!=null){
                stm.close();
            }
        }
    }

    public boolean checkChangeDescription(String itemId, String description) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = Utils.makeConnection();
            String sql = "SELECT description "+
                            " FROM ItemStorage "+
                            " WHERE ID = ? "; 
            stm = con.prepareStatement(sql);
            stm.setString(1, itemId);
            rs = stm.executeQuery();
            if(rs.next()){
                String oldDes = rs.getString(1);
                if(oldDes.compareTo(description) == 0){
                    return false;
                }else{
                    return true;
                }
            }
        }finally {
            if(con != null){
                con.close();
            }
            if(stm != null){
                stm.close();
            }
            if(rs != null){
                rs.close();
            }
        }
        return true;
    }

    public List<ItemStorageDTO> getRecommended_byTopSeller(String StatusID) throws SQLException, NamingException {
        Connection con = null;//1
        PreparedStatement stm = null;//3
        ResultSet rs = null;//4
        List<ItemStorageDTO> reclist= null;
        try {//2
            con = Utils.makeConnection();
            if( con != null){//3
                String sql ="SELECT top (?) ItemStorage.ID, foodName, image, price, quantity, sold "+
                            " FROM ItemStorage "+
                            " WHERE quantity > 0 "+ 
                            " and statusID = ? "+ 
                            " order by sold DESC ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, Utils.recommended);
                stm.setString(2, StatusID);
                rs = stm.executeQuery();
                while(rs.next()){
                    String id = rs.getString(1); 
                    String foodname = rs.getString(2);
                    String image = rs.getString(3);
                    float price = rs.getFloat(4);
                    int quantity = rs.getInt(5);
                    int sold = rs.getInt(6);
                    ItemStorageDTO dto = new ItemStorageDTO(id, foodname, Utils.path+image, null, price, null, 0, quantity, null, sold);
                    if(reclist == null){
                        reclist = new ArrayList<>();
                    }
                    reclist.add(dto);
                }
                return reclist;
            }//end if con
        } finally {
            if(rs != null){
                rs.close();
            }
            if( stm != null){
                stm.close();
            }
            if( con != null){
                con.close();
            }
        }
        return null;
    }

    public List<ItemStorageDTO> getRecommended_byPopular(String StatusID) throws SQLException, NamingException {
        Connection con = null;//1
        PreparedStatement stm = null;//3
        ResultSet rs = null;//4
        List<ItemStorageDTO> reclist= null;
        try {//2
            con = Utils.makeConnection();
            if( con != null){//3
                String sql ="SELECT top (?) ItemStorage.ID, foodName, image, price, quantity, sold "+
                            " FROM ItemStorage, ItemRecord "+
                            " WHERE quantity > 0 "+ 
                            " and ItemStorage.ID = ItemID "+ 
                            " and statusID = ? "+ 
                            " order by PurchaseRecord DESC ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, Utils.recommended);
                stm.setString(2, StatusID);
                rs = stm.executeQuery();
                while(rs.next()){
                    String id = rs.getString(1); 
                    String foodname = rs.getString(2);
                    String image = rs.getString(3);
                    float price = rs.getFloat(4);
                    int quantity = rs.getInt(5);
                    int sold = rs.getInt(6);
                    ItemStorageDTO dto = new ItemStorageDTO(id, foodname, Utils.path+image, null, price, null, 0, quantity, null, sold);
                    if(reclist == null){
                        reclist = new ArrayList<>();
                    }
                    reclist.add(dto);
                }
                return reclist;
            }//end if con
        } finally {
            if(rs != null){
                rs.close();
            }
            if( stm != null){
                stm.close();
            }
            if( con != null){
                con.close();
            }
        }
        return null;
    }

    public int countItem_InCategory(int catID) throws SQLException, NamingException {
        Connection con = null;//1
        PreparedStatement stm = null;//3
        ResultSet rs = null;//4
        try {//2
            con = Utils.makeConnection();
            if( con != null){//3
                String sql ="SELECT count (ID) "+
                            " FROM ItemStorage "+
                            " WHERE categoryID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, catID);
                rs = stm.executeQuery();
                if(rs.next()){
                    int sold = rs.getInt(1);
                    return sold;
                }
            }
        } finally {
            if(rs != null){
                rs.close();
            }
            if( stm != null){
                stm.close();
            }
            if( con != null){
                con.close();
            }
        }
        return 0;
    }
}

