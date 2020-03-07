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
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author TJ
 */
public class UserDAO extends DAO implements UserDAOInterface {
    
    public UserDAO(Connection conn){
        super(conn);
    }
    
    public UserDAO(String databaseName)
    {
        super(databaseName);
    }

    @Override
    public boolean register(String userName, String password, String email) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag = false;

        try {
            con = getConnection();

            String query = "Select email, userName from users where email = ? OR userName = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, userName);
            rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                count++;
            }
            System.out.println("Number of other users with email " + email + "OR UserName " + userName + " : " + count);
            if (count == 0)//rs is 0, there is no duplicates of this username, therefore:
            {
                String hashedPassword = hashPassword(password);
                ps = con.prepareStatement("insert into users (userId, userName, email, password) values (null, ?, ?, ?)");
                ps.setString(1, userName);
                ps.setString(2, email);
                ps.setString(3, hashedPassword);
                ps.executeUpdate();
                ps = con.prepareStatement("INSERT INTO userprofile (userId, active, userType) VALUES ((select userId from users where email = ?), 1, 1);");
                ps.setString(1, email);
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
    public User login(String username, String password) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = getConnection();

            //String query = "Select u.userId, u.username, up.active from users u ,userprofile up where u.userName = ? AND u.password = ? AND up.active = 1";
            String query = "Select userId, userName, password from users where userName = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            int count = 0;
            int userId = 0;
            String userName = "";
            String storedPassword = "";
            while (rs.next()) {
                count++;
                userName = rs.getString("userName");
                userId = rs.getInt("userId");
                storedPassword = rs.getString("password");
            }
            System.out.println("id:" + userId);
            System.out.println("count:" + count);
            if (count == 1)//rs is 1, there is a user with this username, therefore:
            {
                if (checkPassword(password, storedPassword) == true) {
                    String query2 = "SELECT userId, profileId, userType, active FROM userprofile WHERE userId = ?";
                    ps = con.prepareStatement(query2);
                    ps.setInt(1, userId);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        User u = new User(rs.getInt("userId"), rs.getInt("profileId"), rs.getInt("userType"), userName);
                        return u;
                    }
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

    public static String hashPassword(String password_plaintext) {
        int workload = 13;
        String salt = BCrypt.gensalt(workload);
        String hashed_password = BCrypt.hashpw(password_plaintext, salt);

        return (hashed_password);
    }

    public static boolean checkPassword(String password_plaintext, String stored_hash) {
        boolean password_verified = false;

        if (null == stored_hash || !stored_hash.startsWith("$2a$")) {
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
        }

        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

        return (password_verified);
    }
}
