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
public class TblItemDTO implements Serializable{
    private String itemID;
    private String itemname;
    private String description;
    private float price;
    Date createDate;
    private String category;
    private int quantity;
    boolean status;
    private String image;

    
    @Override
    public String toString() {
        return "tblItemDTO{" + "itemID=" + itemID + ", itemname=" + itemname + ", description=" + description + ", price=" + price + ", createDate=" + createDate + ", category=" + category + ", quantity=" + quantity + ", status=" + status + ", image=" + image + '}';
    }

    public TblItemDTO() {
    }

    public TblItemDTO(String itemID, String itemname, String description, float price, Date createDate, String catelogy, int quantity, boolean status, String image) {
        this.itemID = itemID;
        this.itemname = itemname;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
        this.category = catelogy;
        this.quantity = quantity;
        this.status = status;
        this.image = image;
    }

    public TblItemDTO(String itemname, float price, int quantity, String image) {
        this.itemname = itemname;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
    }

    public TblItemDTO(float price) {
        this.price = price;
    }

    public TblItemDTO(String itemname, String description, float price, Date createDate, String category, int quantity, boolean status, String image) {
        this.itemname = itemname;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
        this.category = category;
        this.quantity = quantity;
        this.status = status;
        this.image = image;
    }
    
    
    
    
    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCatelogy(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
}
