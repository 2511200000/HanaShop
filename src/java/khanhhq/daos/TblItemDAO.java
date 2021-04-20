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
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import khanhhq.dtos.TblItemDTO;
import khanhhq.utilities.DbConnection;

/**
 *
 * @author Administrator
 */
public class TblItemDAO implements Serializable {

    public int countAllITemsToSearchAdmin(String searchValue, String id, float priceMin, float priceMax) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbConnection.makeConnection();
            String sql = "Select COUNT(*)"
                    + "from tblItems "
                    + "where itemname LIKE ? AND category LIKE ? AND price >= ? AND price <= ?";
            stm = con.prepareStatement(sql);

            stm.setString(1, "%" + searchValue + "%");

            if (id.equals("")) {
                stm.setString(2, "%" + id + "%");
            } else {
                stm.setString(2, id);
            }
            stm.setFloat(3, priceMin);
            stm.setFloat(4, priceMax);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
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

    private List<TblItemDTO> AccountList;

    public List<TblItemDTO> getAccountList() {
        return AccountList;
    }

    public void searchLastname(String searchValue, String id, float priceMin, float priceMax, int index) throws SQLException, NamingException {
        {
            Connection con = null;
            PreparedStatement stm = null;
            ResultSet rs = null;
            try {
                con = DbConnection.makeConnection();
                if (con != null) {
                    String sql = "with x as(Select ROW_NUMBER() over (order by createDate desc) as r,itemID, itemname, description, price, createDate, category, quantity, status, image "
                            + " From tblItems "
                            + " Where itemname LIKE ? AND category LIKE ? AND price >= ? AND price <= ?)"
                            + " select itemID, itemname, description, price, createDate, category, quantity, status, image from x where r between ?*2-1 and ? * 2";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, "%" + searchValue + "%");

                    if (id.equals("")) {
                        stm.setString(2, "%" + id + "%");
                    } else {
                        stm.setString(2, id);
                    }
                    stm.setFloat(3, priceMin);
                    stm.setFloat(4, priceMax);
                    stm.setInt(5, index);
                    stm.setInt(6, index);
                    rs = stm.executeQuery();
                    while (rs.next()) {
                        String itemID = rs.getString("itemID");
                        String itemname = rs.getString("itemname");
                        String description = rs.getString("description");
                        float price = rs.getFloat("price");
                        Date createDate = rs.getDate("createDate");
                        String category = rs.getString("category");
                        int quantity = rs.getInt("quantity");
                        boolean status = rs.getBoolean("status");
                        String image = rs.getString("image");

                        TblCategoryDAO dao = new TblCategoryDAO();
                        String cateName = dao.getCategoryName(id);
                        if (category.equals(id)) {
                            category = cateName;
                        }

                        String cateOfItem = getCategoryName(itemname);
                        String cateNameOfItem = dao.getCategoryName(cateOfItem);
                        if (searchValue != null) {
                            if (cateOfItem.equals(category)) {
                                category = cateNameOfItem;
                            }
                        }

                        TblItemDTO itemDTO = new TblItemDTO(itemID, itemname, description, price, createDate, category, quantity, status, image);
                        if (this.AccountList == null) {
                            this.AccountList = new ArrayList<>();
                        }
                        this.AccountList.add(itemDTO);
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

    public int countAllITemsToSearch(String searchValue, String id, float priceMin, float priceMax, boolean status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbConnection.makeConnection();
            String sql = "Select COUNT(*)"
                    + "from tblItems "
                    + "where itemname LIKE ? AND category LIKE ? AND price >= ? AND price <= ? AND status = ?";
            stm = con.prepareStatement(sql);

            stm.setString(1, "%" + searchValue + "%");

            if (id.equals("")) {
                stm.setString(2, "%" + id + "%");
            } else {
                stm.setString(2, id);
            }
            stm.setFloat(3, priceMin);
            stm.setFloat(4, priceMax);
            stm.setBoolean(5, status);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
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

    public void searchLastnameStatus(String searchValue, String id, float priceMin, float priceMax, boolean status, int index) throws SQLException, NamingException {
        {
            Connection con = null;
            PreparedStatement stm = null;
            ResultSet rs = null;
            try {
                con = DbConnection.makeConnection();
                if (con != null) {

                    String sql = "with x as(Select ROW_NUMBER() over (order by createDate desc) as r,itemID, itemname, description, price, createDate, category, quantity, status, image "
                            + " From tblItems "
                            + " Where itemname LIKE ? AND category LIKE ? AND price >= ? AND price <= ? AND status = ?)"
                            + " select itemID, itemname, description, price, createDate, category, quantity, status, image from x where r between ?*2-1 and ? * 2";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, "%" + searchValue + "%");

                    if (id.equals("")) {
                        stm.setString(2, "%" + id + "%");
                    } else {
                        stm.setString(2, id);
                    }
                    stm.setFloat(3, priceMin);
                    stm.setFloat(4, priceMax);
                    stm.setBoolean(5, status);
                    stm.setInt(6, index);
                    stm.setInt(7, index);
                    rs = stm.executeQuery();
                    while (rs.next()) {
                        String itemID = rs.getString("itemID");
                        String itemname = rs.getString("itemname");
                        String description = rs.getString("description");
                        float price = rs.getFloat("price");
                        Date createDate = rs.getDate("createDate");
                        String category = rs.getString("category");
                        int quantity = rs.getInt("quantity");
                        boolean statusBoolean = rs.getBoolean("status");
                        if (statusBoolean == status) {
                            statusBoolean = true;
                        }
                        String image = rs.getString("image");

                        TblCategoryDAO dao = new TblCategoryDAO();
                        String cateName = dao.getCategoryName(id);
                        if (category.equals(id)) {
                            category = cateName;
                        }

                        String cateOfItem = getCategoryName(itemname);
                        String cateNameOfItem = dao.getCategoryName(cateOfItem);
                        if (searchValue != null) {
                            if (cateOfItem.equals(category)) {
                                category = cateNameOfItem;
                            }
                        }

                        TblItemDTO itemDTO = new TblItemDTO(itemID, itemname, description, price, createDate, category, quantity, status, image);
                        if (this.AccountList == null) {
                            this.AccountList = new ArrayList<>();
                        }
                        this.AccountList.add(itemDTO);
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

    public String getCategoryName(String itemname) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String name = "";
        try {
            con = DbConnection.makeConnection();
            String sql = "select category "
                    + "from tblItems "
                    + "where itemname = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, itemname);
            rs = stm.executeQuery();
            while (rs.next()) {
                name = rs.getString("category");
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

    public List<TblItemDTO> getAllPrice() throws SQLException, NamingException {
        List<TblItemDTO> list = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbConnection.makeConnection();
            String sql = "select price "
                    + "from tblItems ";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Float price = rs.getFloat("price");
                TblItemDTO item = new TblItemDTO(price);
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(item);
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

    public int countAllITems(boolean status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbConnection.makeConnection();
            String sql = "Select COUNT(*)"
                    + "from tblItems "
                    + "where status = ? AND quantity > 0";
            stm = con.prepareStatement(sql);

            stm.setBoolean(1, status);

            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
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

    private List<TblItemDTO> DataList;

    public List<TblItemDTO> getDataList() {
        return DataList;
    }

    public void printData(boolean statusCheck, int index) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
         try {
            con = DbConnection.makeConnection();
            String sql = "with x as(Select ROW_NUMBER() over (order by createDate desc) as r,itemID, itemname, description, price, createDate, category, quantity, status, image "
                    + " From tblItems "
                    + " Where status = ? AND quantity > 0)"
                    + " select itemID, itemname, description, price, createDate, category, quantity, status, image from x where r between ?*4-3 and ? * 4 ";
            stm = con.prepareStatement(sql);
            stm.setBoolean(1, statusCheck);
            stm.setInt(2, index);
            stm.setInt(3, index);
            rs = stm.executeQuery();
            while (rs.next()) {
                String itemID = rs.getString("itemID");
                String itemname = rs.getString("itemname");
                String description = rs.getString("description");
                float price = rs.getFloat("price");
                Date createDate = rs.getDate("createDate");
                String category = rs.getString("category");

                TblCategoryDAO dao = new TblCategoryDAO();
                String cateName = dao.getCategoryName(category);
                if (cateName != null) {
                    category = cateName;
                }
                int quantity = rs.getInt("quantity");
                boolean status = rs.getBoolean("status");
                if (status == statusCheck) {
                    status = true;
                }
                String image = rs.getString("image");
                TblItemDTO dto = new TblItemDTO(itemID, itemname, description, price, createDate, category, quantity, status, image);
                if (this.DataList == null) {
                    this.DataList = new ArrayList<>();
                }
                this.DataList.add(dto);

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

    private List<TblItemDTO> DataListAdmin;
    private Map<Boolean, String> statusList;

    public List<TblItemDTO> getDataListAdmin() {
        return DataListAdmin;
    }

    public Map<Boolean, String> getStatusList() {
        return statusList;
    }

    public int countAllITemsAdmin() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbConnection.makeConnection();
            String sql = "Select COUNT(*)"
                    + "from tblItems";
            stm = con.prepareStatement(sql);

            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
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

    public void printDataAdmin(int index) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbConnection.makeConnection();
            String sql = "with x as(Select ROW_NUMBER() over (order by createDate desc) as r,itemID, itemname, description, price, createDate, category, quantity, status, image "
                    + " From tblItems)"
                    + " select itemID, itemname, description, price, createDate, category, quantity, status, image from x where r between ?*4-3 and ? * 4";
            stm = con.prepareStatement(sql);
            stm.setInt(1, index);
            stm.setInt(2, index);
            rs = stm.executeQuery();
            while (rs.next()) {
                String itemID = rs.getString("itemID");
                String itemname = rs.getString("itemname");
                String description = rs.getString("description");
                float price = rs.getFloat("price");
                Date createDate = rs.getDate("createDate");
                String category = rs.getString("category");

                TblCategoryDAO dao = new TblCategoryDAO();
                String cateName = dao.getCategoryName(category);
                if (cateName != null) {
                    category = cateName;
                }
                int quantity = rs.getInt("quantity");
                boolean status = rs.getBoolean("status");
                String image = rs.getString("image");
                TblItemDTO dto = new TblItemDTO(itemID, itemname, description, price, createDate, category, quantity, status, image);
                if (this.DataListAdmin == null) {
                    this.DataListAdmin = new ArrayList<>();
                }
                this.DataListAdmin.add(dto);

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

    public void statusBoolean() throws NamingException, SQLException {
        if (this.statusList == null) {
            this.statusList = new HashMap<>();
        }
        this.statusList.put(false, "inactive");
        this.statusList.put(true, "active");
    }

    public boolean deleteItem(String id, boolean status) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DbConnection.makeConnection();
            if (con != null) {
                String sql = "Update tblItems "
                        + "Set status = ? Where itemID = ?";
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, status);
                stm.setString(2, id);
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

    public boolean updateItem(String itemname, String image, String description, float price, String category, boolean status, int quantity, String id) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DbConnection.makeConnection();
            if (con != null) {
                String sql = "Update tblItems "
                        + "Set itemname = ?, image = ?, description = ?, price = ?, category = ?, status = ?, quantity = ? "
                        + "Where itemID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, itemname);
                stm.setString(2, image);
                stm.setString(3, description);
                stm.setFloat(4, price);
                stm.setString(5, category);
                stm.setBoolean(6, status);
                stm.setInt(7, quantity);
                stm.setString(8, id);
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

     public boolean updateQuantity(int quantity, String id) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DbConnection.makeConnection();
            if (con != null) {
                String sql = "Update tblItems "
                        + "Set quantity = ? "
                        + "Where itemID = ?";
                stm = con.prepareStatement(sql);
                
                stm.setInt(1, quantity);
                stm.setString(2, id);
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
    public boolean createItem(String itemID, String itemname, String image, String description, float price, String category, boolean status, int quantity, Date createDate) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
         try {
            con = DbConnection.makeConnection();
            if (con != null) {
                String sql = "Insert into tblItems(itemID, itemname, description, price, createDate, category, status, quantity, image)"
                        + "Values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, itemID);
                stm.setString(2, itemname);
                stm.setString(3, description);
                stm.setFloat(4, price);
                stm.setDate(5, createDate);
                stm.setString(6, category);
                stm.setBoolean(7, status);
                stm.setInt(8, quantity);
                stm.setString(9, image);
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

    public String getItemName(String itemID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String name = "";
        try {
            con = DbConnection.makeConnection();
            String sql = "select itemname "
                    + "from tblItems "
                    + "where itemID = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, itemID);
            rs = stm.executeQuery();
            while (rs.next()) {
                name = rs.getString("itemname");
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

 
    
        public int getQuantityItem(String itemID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbConnection.makeConnection();
            String sql = "select quantity "
                    + "from tblItems "
                    + "where itemID = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, itemID);
            rs = stm.executeQuery();
            while (rs.next()) {
                int quantity = rs.getInt("quantity");
                return quantity;
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
 


    public TblItemDTO findItemByID(String id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbConnection.makeConnection();
            String sql = "Select itemID, itemname, description, price, createDate, category, quantity, status, image "
                    + "from tblItems "
                    + "where itemID = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                String itemID = rs.getString("itemID");
                String itemname = rs.getString("itemname");
                String description = rs.getString("description");
                float price = rs.getFloat("price");
                Date createDate = rs.getDate("createDate");
                String category = rs.getString("category");

                TblCategoryDAO dao = new TblCategoryDAO();
                String cateName = dao.getCategoryName(category);
                if (cateName != null) {
                    category = cateName;
                }
                int quantity = rs.getInt("quantity");
                boolean status = rs.getBoolean("status");

                String image = rs.getString("image");
                TblItemDTO dto = new TblItemDTO(itemID, itemname, description, price, createDate, category, quantity, status, image);
                 return dto;

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
