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
import java.util.List;

/**
 *
 * @author Tom
 */
public class ProfileDAO extends SuperDAO {

    public int addFriend(int userId, int friendId) {
        super.connection();
        boolean check = false;
        int check1 = friendId;
        
        try {

            String query = "INSERT into friends(userId, friendId) values(?, ?)";
            ps = conn.prepareStatement(query);

            ps.setInt(1, userId);
            ps.setInt(2, friendId);

            ps.executeUpdate();
            
        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {

            super.freeConnection();
        }

        return check1;
    }

    public List<Integer> getFriends(int id) {

        super.connection();
        List<Integer> ids = new ArrayList();
        int count = 0;

        try {

            String query = "SELECT * FROM friends WHERE userId = ? OR friendId = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.setInt(2, id);

            rs = ps.executeQuery();

            while (rs.next()) {
                count++;
                ids.add(rs.getInt("friendId"));
            }
        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {

            super.freeConnection();
        }

        return ids;

    }

    public User getUserDetails(int id) {
        super.connection();

        User thisUser = null;

        try {

            String query = "SELECT * FROM userprofile WHERE userId = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            while (rs.next()) {
                thisUser = new User(rs.getInt("userId"), rs.getInt("profileId"), rs.getString("fname"), rs.getString("lname"), rs.getInt("userType"), rs.getInt("active"), rs.getString("address"), rs.getString("dob"), rs.getString("profilePic"));

            }

        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {

            super.freeConnection();
        }

        return thisUser;

    }

    public int profilePic(String filePath, int userId) {
        super.connection();
        int check = 0;

        try {

            ps = conn.prepareStatement("UPDATE userprofile SET profilePic = ? WHERE userId = ?");

            ps.setString(1, filePath);
            ps.setInt(2, userId);

            ps.executeUpdate();

            check = 1;

        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {

            super.freeConnection();
        }
        return check;
    }

}
