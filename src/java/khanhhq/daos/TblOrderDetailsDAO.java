/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhhq.daos;

 import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import khanhhq.utilities.DbConnection;

/**
 *
 * @author Administrator
 */
public class TblOrderDetailsDAO implements Serializable{
    public boolean createOrderDetails(int billID, String itemID, int quantity, float price,float total) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
         try {
            con = DbConnection.makeConnection();
            if (con != null) {
                String sql = "Insert into tblOrderDetails(billID,itemID,quantity,price,total)"
                        + "Values(?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, billID);
                stm.setString(2, itemID);
                stm.setInt(3, quantity);
                stm.setFloat(4, price);
                stm.setFloat(5, total);
                int row = stm.executeUpdate();

                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
