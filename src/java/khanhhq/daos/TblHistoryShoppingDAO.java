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
import khanhhq.dtos.TblHistoryShoppingDTO;
import khanhhq.utilities.DbConnection;

/**
 *
 * @author Administrator
 */
public class TblHistoryShoppingDAO implements Serializable {

    public boolean createHistoryShopping(int orderID, String itemID, String userID, Date date, int quantity, float totalAll) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DbConnection.makeConnection();
            if (con != null) {
                String sql = "Insert into tblHistoryShopping(orderID, itemID, userID, date, quantity, totalAll)"
                        + "Values(?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderID);
                stm.setString(2, itemID);
                stm.setString(3, userID);
                stm.setDate(4, date);
                stm.setInt(5, quantity);
                stm.setFloat(6, totalAll);
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
    private List<TblHistoryShoppingDTO> listHistoryShopping;

    public List<TblHistoryShoppingDTO> getlistHistoryShopping() {
        return listHistoryShopping;
    }

    public void printHistoryShopping(String userIDCheck) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DbConnection.makeConnection();
            String sql = "Select orderID, userID, itemID, date, quantity, totalAll "
                    + " from tblHistoryShopping "
                    + "where userID = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, userIDCheck);
            rs = stm.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                String userID = rs.getString("userID");

                String itemID = rs.getString("itemID");
                TblItemDAO dao = new TblItemDAO();
                String itemname = dao.getItemName(itemID);
                TblLoginDAO daoLogin = new TblLoginDAO();
                String username = daoLogin.getUsername(userID);

                Date createDate = rs.getDate("date");
                int quantity = rs.getInt("quantity");
                float totalAll = rs.getFloat("totalAll");

                TblHistoryShoppingDTO dto = new TblHistoryShoppingDTO(orderID, username, itemname, createDate, quantity, totalAll);
                if (this.listHistoryShopping == null) {
                    this.listHistoryShopping = new ArrayList<>();
                }
                this.listHistoryShopping.add(dto);
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
    private List<TblHistoryShoppingDTO> searchHistory;

    public List<TblHistoryShoppingDTO> getSearchHistory() {
        return searchHistory;
    }

    public void searchHistory(String itemIDCheck, String dateCheck, String userIDCheck) throws SQLException, NamingException {
        {
            Connection con = null;
            PreparedStatement stm = null;
            ResultSet rs = null;
            try {
                con = DbConnection.makeConnection();
                if (con != null) {
                    String sql = "Select orderID, itemID, userID, date, quantity, totalAll "
                            + "From tblHistoryShopping "
                            + "Where itemID in (select itemID from tblItems where itemname LIKE ?) AND date LIKE ? AND userID = ?";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, "%" + itemIDCheck + "%");
                    stm.setString(2, "%" + dateCheck + "%");
                    stm.setString(3, userIDCheck);
                    rs = stm.executeQuery();
                    while (rs.next()) {
                        int orderID = rs.getInt("orderID");
                        String itemID = rs.getString("itemID");
                         String userID = rs.getString("userID");
                        TblItemDAO dao = new TblItemDAO();
                        String itemname = dao.getItemName(itemID);
                        TblLoginDAO daoLogin = new TblLoginDAO();
                        String username = daoLogin.getUsername(userID);
                       
                        Date date = rs.getDate("date");
                        int quantity = rs.getInt("quantity");
                        float totalAll = rs.getFloat("totalAll");

                        TblHistoryShoppingDTO dto = new TblHistoryShoppingDTO(orderID, username, itemname, date, quantity, totalAll);
                        if (this.searchHistory == null) {
                            this.searchHistory = new ArrayList<>();
                        }
                        this.searchHistory.add(dto);
                    }
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
    }
}
