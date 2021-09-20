/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhcn.users;

import java.io.Serializable;

/**
 *
 * @author nguye
 */
public class UserDTO implements Serializable{

    private String userID;
    private String password;
    private String role;
    private String fullName;

    public UserDTO(String userID, String password, String role, String fullName) {
        this.userID = userID;
        this.password = password;
        this.role = role;
        this.fullName = fullName;
    }

    public UserDTO(String role, String fullName) {
        this.role = role;
        this.fullName = fullName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
