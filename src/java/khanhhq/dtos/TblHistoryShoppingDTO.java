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
public class TblHistoryShoppingDTO implements Serializable{
    private int orderID;
    private String userID;
    private String itemID;
    private Date date;
    private int quantity;
    private float totalAll;

    public TblHistoryShoppingDTO(int orderID, String userID, String itemID, Date date, int quantity, float totalAll) {
        this.orderID = orderID;
        this.userID = userID;
        this.itemID = itemID;
        this.date = date;
        this.quantity = quantity;
        this.totalAll = totalAll;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotalAll() {
        return totalAll;
    }

    public void setTotalAll(float totalAll) {
        this.totalAll = totalAll;
    }
 
    

   
   
    
}
