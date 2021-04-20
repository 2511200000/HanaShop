/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhhq.daos;

 import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import khanhhq.dtos.TblHistoryDTO;
import khanhhq.utilities.DbConnection;

/**
 *
 * @author Administrator
 */
public class TblHistoryDAO implements Serializable {

    public boolean createHistory(int id, String itemID, String userID, Date date, String action) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        

        try {
            con = DbConnection.makeConnection();
            if (con != null) {
                String sql = "Insert into tblHistory(id,  userID,itemID, date, action)"
                        + "Values(?, ?, ?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                stm.setString(3, itemID);
                stm.setString(2, userID);
                stm.setDate(4, date);
                stm.setString(5, action);
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
    private List<TblHistoryDTO> listHistory;

    public List<TblHistoryDTO> getlistHistory() {
        return listHistory;
    }

    public void printHistory(String userIDCheck) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
      
        try {
            con = DbConnection.makeConnection();
            String sql = "Select id,userID,itemID,date,action "
                    + " from tblHistory "
                    + "where userID = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, userIDCheck);
            rs = stm.executeQuery();
            while (rs.next()) {             
                 String id = rs.getString("id");               
                String itemID = rs.getString("itemID");    
                
                TblItemDAO dao = new TblItemDAO();       
                String itemname = dao.getItemName(itemID);
                
                String userID = rs.getString("userID");
                
                TblLoginDAO daoLogin = new TblLoginDAO();
                String username = daoLogin.getUsername(userID);
                
                Date createDate = rs.getDate("date");
                String action = rs.getString("action");
                
               TblHistoryDTO dto = new TblHistoryDTO(id, itemname, username, createDate, action);
                 if (this.listHistory == null) {
                    this.listHistory = new ArrayList<>();
                }
                this.listHistory.add(dto);
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

    public int printIdHistory() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbConnection.makeConnection();
            String sql = "Select id=MAX(id) from tblHistory";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
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
