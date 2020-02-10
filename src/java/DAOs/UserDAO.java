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
    public boolean register(String userName, String password, String email) 
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag = false;

        try {
            con = getConnection();

            String query = "Select userName from users where userName = ?";
            ps = con.prepareStatement(query);
            ps.setString(1,userName);
            rs = ps.executeQuery();
            int count = 0;
            while(rs.next()){
                count++;
            }
            System.out.println("Number of other users with username "+ userName + " : " + count);
            if(count == 0)//rs is 0, there is no duplicates of this username, therefore:
            {
                ps = con.prepareStatement("insert into users (userId, username, password) values (null, ?, ?)");
                ps.setString(1, userName);
                ps.setString(2, password);
                ps.executeUpdate();
                ps = con.prepareStatement("INSERT INTO userprofile (userId, email, active, userType) VALUES ((select userId from users where username = ?), ?, 1, 1);");
                ps.setString(1, userName);
                ps.setString(2, email);
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
        try
        {
            con = getConnection();

            //String query = "Select u.userId, u.username, up.active from users u ,userprofile up where u.userName = ? AND u.password = ? AND up.active = 1";
            String query = "Select u.userId, u.username from users u where u.userName = ? AND u.password = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);     
            ps.setString(2, password);
            rs = ps.executeQuery();
            int count = 0;
            int userId = 0;
            while(rs.next()){
                count++;
                userId = rs.getInt("userId");
            }
            System.out.println("id:"+userId);
            System.out.println("count:"+count);
            if(count == 1)//rs is 1, there is only one username that holds this password, therefore:
            {
                String query2 = "SELECT userId, profileId, userType, active FROM userprofile WHERE userId = ?";
                ps = con.prepareStatement(query2);
                ps.setInt(1, userId);
                rs = ps.executeQuery();
                while(rs.next()){
                    User u = new User(rs.getInt("userId"), rs.getInt("profileId"), rs.getInt("userType"), rs.getInt("active") );
                    return u;
                }
                
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
