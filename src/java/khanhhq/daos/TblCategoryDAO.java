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
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import khanhhq.dtos.TblCategoryDTO;
import khanhhq.utilities.DbConnection;

/**
 *
 * @author Administrator
 */
public class TblCategoryDAO implements Serializable {

    public List<TblCategoryDTO> getAllCategory() throws SQLException, NamingException {
        List<TblCategoryDTO> list = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbConnection.makeConnection();
            String sql = "select category, categoryname "
                    + "from tblCategoryID ";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                String code = rs.getString("category");
                String name = rs.getString("categoryname");
                TblCategoryDTO cate = new TblCategoryDTO(code, name);
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(cate);
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
        return list;
    }

    public String getCategoryName(String id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String name = "";
        try {
            con = DbConnection.makeConnection();
            String sql = "select categoryname "
                    + "from tblCategoryID "
                    + "where category = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                name = rs.getString("categoryname");
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
