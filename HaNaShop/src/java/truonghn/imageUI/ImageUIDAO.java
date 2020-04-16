/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.imageUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
public class ImageUIDAO {
    public String getImage(String name) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = Utils.makeConnection();
            if(con !=null){
                String sql = "select image"
                        + " from imageUI"
                        + " where name = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, name);
                rs = stm.executeQuery();
                
                if(rs.next()){
                    return rs.getString(1);
                }
                else return "commingSoon.jpg";
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
        return "commingSoon.jpg";
    }
}
