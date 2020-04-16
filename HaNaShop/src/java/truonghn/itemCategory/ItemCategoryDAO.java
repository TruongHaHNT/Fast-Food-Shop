/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.itemCategory;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
public class ItemCategoryDAO implements Serializable{
    private List<ItemCategoryDTO> list;

    public List<ItemCategoryDTO> getList() {
        return list;
    }
    
    public List<ItemCategoryDTO> getCategoryList() throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = Utils.makeConnection();
            if(con !=null){
                String sql = "select categoryID, category "
                        + " from ItemCategory ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {                    
                    int catID = rs.getInt(1);
                    String cat = rs.getString(2);
                    ItemCategoryDTO dto = new ItemCategoryDTO(catID, cat);
                    if(list == null){
                        list = new ArrayList<>();
                    }
                    list.add(dto);
                }
                return this.list;
                
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
        return null;
    }
    
    public List<ItemCategoryDTO> getCategoryList(String StatusID) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = Utils.makeConnection();
            if(con !=null){
                String sql = "select distinct ItemCategory.categoryID, ItemCategory.category "
                        + " from ItemCategory, ItemStorage "
                        + " where ItemCategory.categoryID = ItemStorage.categoryID "
                        + " and ItemStorage.quantity > 0 "
                        + " and statusID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, StatusID);
                rs = stm.executeQuery();
                while (rs.next()) {                    
                    int catID = rs.getInt(1);
                    String cat = rs.getString(2);
                    ItemCategoryDTO dto = new ItemCategoryDTO(catID, cat);
                    if(list == null){
                        list = new ArrayList<>();
                    }
                    list.add(dto);
                }
                return this.list;
                
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
        return null;
    }
    
    public boolean checkExisted(String cat) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = Utils.makeConnection();
            if(con !=null){
                String sql ="select category"
                        + " from ItemCategory"
                        + " where category like ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + cat + "%");
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
    
    public void addNewCategory(String cat) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = Utils.makeConnection();
            if(con !=null){
                String sql = "insert into ItemCategory(category) "
                        + "Values (?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, cat);
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
    
    public int getCatID(String cat) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = Utils.makeConnection();
            if(con !=null){
                String sql ="select categoryID"
                        + " from ItemCategory"
                        + " where category = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, cat);
                rs = stm.executeQuery();
                if(rs.next()){
                    return rs.getInt(1);
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
        return 0;
    }

    public String getCategory(int catID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = Utils.makeConnection();
            if(con !=null){
                String sql ="select category "
                        + " from ItemCategory "
                        + " where categoryID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, catID);
                rs = stm.executeQuery();
                if(rs.next()){
                    return rs.getString(1);
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
        return "";
    }

    public void updateCat(int catID, String value) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = Utils.makeConnection();
            
            if(con != null){
                String sql ="update ItemCategory set category = ? " +
                            " where categoryID = ? ";
                
                stm = con.prepareStatement(sql);
                stm.setString(1, value);
                stm.setInt(2, catID);
                
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

    public void deleteCat(int catID) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = Utils.makeConnection();
            
            if(con != null){
                String sql ="delete from ItemCategory " +
                            " where categoryID = ? ";
                
                stm = con.prepareStatement(sql);
                stm.setInt(1, catID);
                
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
}
