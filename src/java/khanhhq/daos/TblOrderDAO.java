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
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import khanhhq.dtos.TblOrderDTO;
import khanhhq.utilities.DbConnection;

/**
 *
 * @author Administrator
 */
public class TblOrderDAO implements Serializable{
     public boolean createOrder(int billID, int customerID, Date dateOrder, float totalAll) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DbConnection.makeConnection();
            if (con != null) {
                String sql = "Insert into tblOrder(orderID,customerID,dateOrder,totalAll)"
                        + "Values(?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, billID);
                stm.setInt(2, customerID);
                stm.setDate(3, dateOrder);
                stm.setFloat(4, totalAll);
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
     private List<TblOrderDTO> listOrder;

    public List<TblOrderDTO> getlistOrder() {
        return listOrder;
    }

    public void printOrder(int billMAX, int customerBillId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
      
        try {
            con = DbConnection.makeConnection();
            String sql = "Select orderID,customerID,dateOrder,totalAll from tblOrder where orderID = ? AND customerID = ?";
            stm = con.prepareStatement(sql);
            stm.setInt(1, billMAX);
            stm.setInt(2, customerBillId);
            rs = stm.executeQuery();
            while (rs.next()) {             
                 String orderID = rs.getString("orderID");               
                String customerID = rs.getString("customerID"); 
                TblCustomerDAO dao = new TblCustomerDAO();
                
                String customname = dao.printNameCustomer(customerID);
                Date dateOrder = rs.getDate("dateOrder");
              
                float totalAll = rs.getFloat("totalAll");
                
              TblOrderDTO dto = new TblOrderDTO(orderID, customname, totalAll, dateOrder);
                 if (this.listOrder == null) {
                    this.listOrder = new ArrayList<>();
                }
                this.listOrder.add(dto);
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    public int printIdOrder() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbConnection.makeConnection();
            String sql = "Select orderID=MAX(orderID) from tblOrder";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("orderID");
                return id;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return 0;
    }
}
