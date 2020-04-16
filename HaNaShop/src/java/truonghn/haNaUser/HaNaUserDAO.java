/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.haNaUser;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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
public class HaNaUserDAO implements Serializable{
    public boolean checkLogin(String email, String password) throws SQLException, NamingException, NoSuchAlgorithmException, UnsupportedEncodingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = Utils.makeConnection();
            if(con !=null){
                String sql = "select mail, password"
                        + " from HaNaUser"
                        + " where mail = ? and password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                password = Utils.encryptPass(password);
                stm.setString(2, password);
                
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
    
    public boolean isAdmin(String email) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = Utils.makeConnection();
            if(con !=null){
                String sql ="select HaNaUserRole.role"
                        + " from HaNaUser, HaNaUserRole"
                        + " where HaNaUser.roleID = HaNaUserRole.roleID"
                        + " and mail = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if(rs.next()){
                    if(rs.getString(1).matches("Manager")){
                        return true;
                    }
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
    
    public boolean isActive(String email) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = Utils.makeConnection();
            if(con !=null){
                String sql ="select HaNaUserStatus.status"
                        + " from HaNaUser, HaNaUserStatus"
                        + " where HaNaUser.statusID = HaNaUserStatus.statusID"
                        + " and mail = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if(rs.next()){
                    if(rs.getString(1).matches("Active")){
                        return true;
                    }
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

    private List<HaNaUserDTO> list = null;

    public List<HaNaUserDTO> getList() {
        return list;
    }
    
    public HaNaUserDTO getUserinfo(String email, String password) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        HaNaUserDTO dto = null;
        try {
            con = Utils.makeConnection();
            if(con !=null){
                String sql ="select HaNaUser.mail, HaNaUser.name, HaNaUser.password, HaNaUserRole.role, HaNaUserStatus.status"
                        + " from HaNaUser, HaNaUserRole, HaNaUserStatus"
                        + " where HaNaUser.roleID = HaNaUserRole.roleID"
                        + " and HaNaUser.statusID = HaNaUserStatus.statusID"
                        + " and HaNaUser.mail = ? and HaNaUser.password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                password = Utils.encryptPass(password);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if(rs.next()){
                    String useremail = rs.getString(1);
                    String username = rs.getString(2);
                    String userPass = rs.getString(3);
                    String role = rs.getString(4);
                    String status = rs.getString(5);
                    dto = new HaNaUserDTO(useremail,username, userPass, role, status);
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
        return dto;
    }
    
    public boolean checkExisted(String email) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = Utils.makeConnection();
            if(con !=null){
                String sql ="select mail"
                        + " from HaNaUser"
                        + " where mail = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
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
    
    public void activeUserStatus(String email, String statusID) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = Utils.makeConnection();
            
            if(con != null){
                String sql ="update HaNaUser set statusID = ?" +
                            " where mail = ?";
                
                stm = con.prepareStatement(sql);
                stm.setString(1, statusID);
                stm.setString(2, email);
                
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
     
    public void createNewAccount(String email, String name, String password, String roleID, String statusID) throws SQLException, NamingException, NoSuchAlgorithmException, UnsupportedEncodingException{
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = Utils.makeConnection();
            
            if(con != null){
                String sql = "insert into HaNaUser(mail, name, password, roleID, statusID) "
                        + "Values (?,?,?,?,?)";
                
                stm = con.prepareStatement(sql);
                stm.setString(1, email.toLowerCase());
                stm.setString(2, name);
                password = Utils.encryptPass(password);
                stm.setString(3, password);
                stm.setString(4, roleID);
                stm.setString(5, statusID);
                
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

    public void getUserList(String userName,int curPage, int totalUserPrePage) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int buttonPage = totalUserPrePage*(curPage-1)+1;
        int topPage = totalUserPrePage*curPage;
        try {
            con = Utils.makeConnection();
            if(con !=null){
                String sql =" select mail, name, role, status "
                        + " from (select HaNaUser.mail, HaNaUser.name, HaNaUserRole.role, HaNaUserStatus.status, ROW_NUMBER () OVER (ORDER BY HaNaUser.name desc) AS RowNum "
                        + " from HaNaUser, HaNaUserRole, HaNaUserStatus "
                        + " where HaNaUser.roleID = HaNaUserRole.roleID "
                        + " and HaNaUser.statusID = HaNaUserStatus.statusID "
                        + " and HaNaUser.name like ?) as list"
                        + " WHERE RowNum >= ? and RowNum <= ? ";;
                stm = con.prepareStatement(sql);
                stm.setString(1, "%"+userName+"%");
                stm.setInt(2, buttonPage);
                stm.setInt(3, topPage);
                rs = stm.executeQuery();
                while(rs.next()){
                    String useremail = rs.getString(1);
                    String username = rs.getString(2);
                    String role = rs.getString(3);
                    String status = rs.getString(4);
                    if(list == null){
                        list = new ArrayList<>();
                    }
                    HaNaUserDTO dto = new HaNaUserDTO(useremail, username, null, role, status);
                    list.add(dto);
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
    }

    public int getTotalPage(String userName, Integer totalContentPerPage) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        float page = 0;
        try {
            con = Utils.makeConnection();
            if(con !=null){
                String sql =" select count (mail) " +
                           " from HaNaUser " +
                           " where name like ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%"+userName+"%");
                rs = stm.executeQuery();
                if(rs.next()){
                    page = (float)rs.getInt(1);
                    if(page>0){
                        return (int)Math.ceil((page/totalContentPerPage));
                    }
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
}
