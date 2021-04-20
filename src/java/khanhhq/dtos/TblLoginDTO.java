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
public class TblLoginDTO implements Serializable{
    private String userID;
    private String username;
    private String password;
    private String roleID;
    private int id;

    public TblLoginDTO() {
    }

    public TblLoginDTO(String userID, String username, String password, String roleID) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.roleID = roleID;
    }
    
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }
    
    
}
