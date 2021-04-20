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
public class TblCategoryDTO implements Serializable{
    private String category;
    private String categoryname;

    public TblCategoryDTO(String category, String categoryname) {
        this.category = category;
        this.categoryname = categoryname;
    }

    public TblCategoryDTO(String categoryname) {
        this.categoryname = categoryname;
    }
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }
    
}
