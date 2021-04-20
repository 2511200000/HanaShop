/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhhq.dtos;

 import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class TblHistoryDTO implements Serializable{
    private String id;
    private String itemID;
    private String userID;
    private Date date;
    private String action;

    public TblHistoryDTO(String id, String itemID, String userID, Date date, String action) {
        this.id = id;
        this.itemID = itemID;
        this.userID = userID;
        this.date = date;
        this.action = action;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

   

   
    
            
}
