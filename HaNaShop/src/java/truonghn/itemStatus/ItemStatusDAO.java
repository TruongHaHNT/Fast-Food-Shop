/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.itemStatus;

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
public class ItemStatusDAO implements Serializable{
    public String getStatusID(String status) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = Utils.makeConnection();
            if(con != null){
                String sql = "select statusID "
                        + " from ItemStatus "
                        + " where status = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, status);
                rs = stm.executeQuery();
                if(rs.next()){
                    return rs.getString(1);
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
        return "";
    }

    List<ItemStatusDTO> list = null;

    public List<ItemStatusDTO> getList() {
        return list;
    }
        
    public void getStatusList() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = Utils.makeConnection();
            if(con != null){
                String sql = "select status, statusID "
                        + " from ItemStatus ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while(rs.next()){
                    String status = rs.getString(1);
                    String statusID = rs.getString(2);
                    ItemStatusDTO dto = new ItemStatusDTO(statusID, status);
                    if(list == null){
                        list = new ArrayList<>();
                    }
                    list.add(dto);
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
}
