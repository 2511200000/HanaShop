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
public class TblOrderDTO implements Serializable{
    private String orderID;
    private String custommerID;
    private float totalAll;
    private Date dateOrder;

    public TblOrderDTO(String orderID, String custommerID, float totalAll, Date dateOrder) {
        this.orderID = orderID;
        this.custommerID = custommerID;
        this.totalAll = totalAll;
        this.dateOrder = dateOrder;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCustommerID() {
        return custommerID;
    }

    public void setCustommerID(String custommerID) {
        this.custommerID = custommerID;
    }

    public float getTotalAll() {
        return totalAll;
    }

    public void setTotalAll(float totalAll) {
        this.totalAll = totalAll;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

   
    
}
