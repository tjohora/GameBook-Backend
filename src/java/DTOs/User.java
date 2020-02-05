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
    private int profileId;
    private String username;
    private String password;
    private String fname;
    private String lname;
    private int userType;
    private int active;
    private String email;
    private String address;
    private String dob;
    //private String joinDate;

    public User(int userId, int profileId, String username, String password, String fname, String lname, int userType, int active, String email, String address, String dob) {
        this.userId = userId;
        this.profileId = profileId;
        this.username = username;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.userType = userType;
        this.active = active;
        this.email = email;
        this.address = address;
        this.dob = dob;
    }

    public User(int userId, String username, String email, String password, int active, int userType) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.active = active;
        this.userType = userType;  
    }
    
    public User(int userId, String username, String email, int userType, String joinDate) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.userType = userType;
        this.active = 1;
    }

    public User(int userId, int profileId, int userType) {
        this.userId = userId;
        this.profileId = profileId;
        this.userType = userType;
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

    public int getActive() {
        return active;
    }

    public void setActive(int status) {
        this.active = status;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
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
        return "User{" + "userId=" + userId + ", username=" + username + ", password=" + password + ", fname=" + fname + ", lname=" + lname + ", userType=" + userType + ", active=" + active + ", email=" + email + ", address=" + address + ", dob=" + dob + '}';
    }
    
}
