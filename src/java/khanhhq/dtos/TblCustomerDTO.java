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
public class TblCustomerDTO implements Serializable{
    private String customerID;
    private String customername;
    private String address;
    private String phonenumber;

    public TblCustomerDTO(String customerID, String customername, String address, String phonenumber) {
        this.customerID = customerID;
        this.customername = customername;
        this.address = address;
        this.phonenumber = phonenumber;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
    
}
