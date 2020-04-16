/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.itemRecord;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
public class ItemRecordDAO implements Serializable{
    private List<ItemRecordDTO> list;

    public List<ItemRecordDTO> getList() {
        return list;
    }

    public void recordItem(String itemID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = Utils.makeConnection();
            
            if(con != null){
                String sql ="update ItemRecord set PurchaseRecord = PurchaseRecord + 1 "
                            + " where ItemID = ? ";
                
                stm = con.prepareStatement(sql);
                stm.setString(1, itemID);
                
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

    public void createRecord(String itemId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = Utils.makeConnection();
            
            if(con != null){
                String sql = "insert into ItemRecord(ItemID, PurchaseRecord) "
                        + "Values (?,?)";
                
                stm = con.prepareStatement(sql);
                stm.setString(1, itemId);
                stm.setInt(2, 0);
                
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
