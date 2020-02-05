/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import java.util.Objects;

/**
 *
 * @author TJ
 */
public class User {
    private int userId;
    private String username;
    private String email;
    private String password;
    private int status;
    private int userType;
    private String joinDate;

    public User(int userId, String username, String email, String password, int status, int userType, String joinDate) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = status;
        this.userType = userType;
        this.joinDate = joinDate;
    }
    
    public User(int userId, String username, String email, int userType, String joinDate) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.userType = userType;
        this.joinDate = joinDate;
        this.status = 1;
    }

    public User() {
        
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.userId;
        hash = 71 * hash + Objects.hashCode(this.username);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.userId != other.userId) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", username=" + username + ", email=" + email + ", password=" + password + ", status=" + status + ", userType=" + userType + ", joinDate=" + joinDate + '}';
    }
    
    
}
