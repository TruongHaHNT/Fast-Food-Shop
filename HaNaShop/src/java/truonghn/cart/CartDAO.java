/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.cart;

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
public class CartDAO implements Serializable{

    public void addToCart(String userID, String itemID, int amount) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = Utils.makeConnection();
            
            if(con != null){
                String sql = "insert into cart(mail, foodID, amount) "
                        + "Values (?,?,?)";
                
                stm = con.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, itemID);
                stm.setInt(3, amount);
                
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

    public void updateCart(String userID, String itemID, int amount) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = Utils.makeConnection();
            
            if(con != null){
                String sql ="update cart set amount = ? "
                            + " where mail = ? "
                            + " and foodID = ? ";
                
                stm = con.prepareStatement(sql);
                stm.setInt(1, amount);
                stm.setString(2, userID);
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

    public CartDTO getItemFromCart(String userID, String itemID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = Utils.makeConnection();
            if(con !=null){
                String sql = " select amount "
                        + " from cart "
                        + " where mail = ? "
                        + " and foodID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, itemID);
                
                rs = stm.executeQuery();
                
                if(rs.next()){
                    int amount = rs.getInt(1);
                    CartDTO dto = new CartDTO(userID, itemID, amount);
                    return dto;
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
        return null;
    }
    
    private List<CartDTO> list = null;

    public List<CartDTO> getList() {
        return list;
    }

    public void getCartList(String userID) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = Utils.makeConnection();
            if(con != null){
                String sql = " select foodID, amount "
                            + " from cart "
                            + " where mail = ? ";
                
               stm = con.prepareStatement(sql);
               stm.setString(1, userID);
               rs = stm.executeQuery();
               while(rs.next()){
                   String foodID = rs.getString(1);
                   int amount = rs.getInt(2);
                   if(this.list == null){
                       this.list = new ArrayList<>();
                   }
                   
                   CartDTO dto = new CartDTO(userID, foodID, amount);
                   this.list.add(dto);
               }
            }   
        } finally {
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

    public void deleteCart(String userID, String itemID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = Utils.makeConnection();
            
            if(con != null){
                String sql =" delete from cart "
                            + " where mail = ? "
                            + " and foodID = ? ";
                
                stm = con.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, itemID);
                
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
