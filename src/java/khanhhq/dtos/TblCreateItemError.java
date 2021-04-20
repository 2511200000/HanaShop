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
public class TblCreateItemError implements Serializable{
    public String priceErr;
    public String quantityErr;
    public String itemIDisExist;
     public String errNumberFormat;
     public String errBlank;

    public TblCreateItemError() {
    }

     
    public TblCreateItemError(String priceErr, String quantityErr, String itemIDisExist, String errNumberFormat, String errBlank) {
        this.priceErr = priceErr;
        this.quantityErr = quantityErr;
        this.itemIDisExist = itemIDisExist;
        this.errNumberFormat = errNumberFormat;
        this.errBlank = errBlank;
    }

    public String getPriceErr() {
        return priceErr;
    }

    public void setPriceErr(String priceErr) {
        this.priceErr = priceErr;
    }

    public String getQuantityErr() {
        return quantityErr;
    }

    public void setQuantityErr(String quantityErr) {
        this.quantityErr = quantityErr;
    }

    public String getItemIDisExist() {
        return itemIDisExist;
    }

    public void setItemIDisExist(String itemIDisExist) {
        this.itemIDisExist = itemIDisExist;
    }

    public String getErrNumberFormat() {
        return errNumberFormat;
    }

    public void setErrNumberFormat(String errNumberFormat) {
        this.errNumberFormat = errNumberFormat;
    }

    public String getErrBlank() {
        return errBlank;
    }

    public void setErrBlank(String errBlank) {
        this.errBlank = errBlank;
    }

    
    
}
