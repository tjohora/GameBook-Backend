/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author TJ
 */
public class UserDAO extends DAO implements UserDAOInterface 
{

    @Override
    public boolean register(User u) 
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag = false;

        try {
            con = getConnection();

            String query = "Select userName from users where userName = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, u.getUsername());
            rs = ps.executeQuery();
            int count = 0;
            while(rs.next()){
                count++;
            }
            System.out.println("Number of other users with username "+ u.getUsername() + " : " + count);
            if(count == 0)//rs is 0, there is no duplicates of this username, therefore:
            {
                ps = con.prepareStatement("insert into user (userId, username, email, password, status, userType, joinDate) values (null, ?, ?, ?, 1, 1,NOW())");
                ps.setString(1, u.getUsername());
                ps.setString(2, u.getEmail());
                ps.setString(3, u.getPassword());
                ps.executeUpdate();
                System.out.println("User has been added.");
                flag = true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the register() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the register() method: " + e.getMessage());
            }
        }
        return flag;
    }

    @Override
    public User login(String username, String password)
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<User> users = new ArrayList();
        try
        {
            con = getConnection();

            String query = "Select userId, username, email, status, userType, joinDate from user where username = ? AND password = ? AND status = 1";
            ps = con.prepareStatement(query);
            ps.setString(1, username);     
            ps.setString(2, password);
            rs = ps.executeQuery();
            int count = 0;
            
            while(rs.next()){
                count++;
            }
            if(count == 1)//rs is 1, there is only one username that holds this password, therefore:
            {
                rs.first();
                User u = new User(rs.getInt("userId"), rs.getString("username"), rs.getString("email"),rs.getInt("userType"), rs.getString("joinDate"));
                return u;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the login() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the login() method: " + e.getMessage());
            }
        }
        User u = new User();
        return u;
    }
    
}
