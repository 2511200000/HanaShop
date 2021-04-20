/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhhq.daos;

 import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import khanhhq.utilities.DbConnection;

/**
 *
 * @author Administrator
 */
public class TblCustomerDAO implements Serializable {

    public boolean createCustomer(int id, String customername, String address, String phonename) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DbConnection.makeConnection();
            if (con != null) {
                String sql = "Insert into tblCustomer(customerID, customername, address, phonenumber)"
                        + "Values(?, ?, ?,?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                stm.setString(3, address);
                stm.setString(2, customername);
                stm.setString(4, phonename);
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
    public int printIdCustomer() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbConnection.makeConnection();
            String sql = "Select customerID=MAX(customerID) from tblCustomer";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("customerID");
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
    public String printNameCustomer(String customerID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbConnection.makeConnection();
            String sql = "Select customername from tblCustomer where customerID = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, customerID);
            rs = stm.executeQuery();
            if (rs.next()) {
                 String name = rs.getString("customername");
                return name;
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

        return null;
    }
}
