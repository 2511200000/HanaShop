/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhhq.dtos;

 import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class TblOrderDetailsDTO implements Serializable{
    private String billID;
    private String itemID;
    private int quantity;
    private float price;
    private float toal;

    public TblOrderDetailsDTO(String billID, String itemID, int quantity, float price, float toal) {
        this.billID = billID;
        this.itemID = itemID;
        this.quantity = quantity;
        this.price = price;
        this.toal = toal;
    }

    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getToal() {
        return toal;
    }

    public void setToal(float toal) {
        this.toal = toal;
    }

   
    
}
