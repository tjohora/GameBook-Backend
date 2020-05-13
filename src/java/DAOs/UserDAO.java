/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public List<User> getAllUsers() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> users = new ArrayList();
        
        try{
            con = getConnection();
            String query = "Select u.userId, u.userName, u.email, up.profileId, up.fname, up.lname, up.userType, up.address, up.dob, up.active from users u inner join userprofile up on u.userId = up.userId"; 
            ps = con.prepareStatement(query);
            rs = ps.executeQuery(); 
            
            while(rs.next())
            {
                User u = new User(rs.getInt("userId"), rs.getInt("profileId"), rs.getString("username"), rs.getString("fname"), rs.getString("lname"), rs.getInt("userType"), rs.getInt("active"), rs.getString("email"), rs.getString("address"), rs.getString("dob"));
                users.add(u);
            }
        }catch (SQLException e) {
            System.out.println("Exception occured in the getAllUsers() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getAllUsers() method: " + e.getMessage());
            }
        }
        
        return users;
    }
    
    public boolean deleteUser(int userId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag = false;       
        try
        {
            con = getConnection();
            ps = con.prepareStatement("UPDATE users SET username='Deleted', email='Deleted', password='Deleted' WHERE userId = ?");
            ps.setInt(1, userId);
            ps.executeUpdate();
            ps = con.prepareStatement("UPDATE userprofile SET fname='Deleted', lname='Deleted', address='Deleted', dob='Deleted', active = 0 WHERE userId = ?");
            ps.setInt(1, userId);
            ps.executeUpdate();
            System.out.println("User has been deleted.");
            flag = true;
        }catch (SQLException e) {
            System.out.println("Exception occured in the deletePost() method: " + e.getMessage());
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
                System.out.println("Exception occured in the final section of the deleteUser() method: " + e.getMessage());
            }
        }
        return flag;
    }
    
    public boolean editUser(String fname, String lname, String address, Date dob, int userId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag = false;
        System.out.println(dob);
        try {

            con = getConnection();

            ps = con.prepareStatement("UPDATE userprofile SET fname = ?, lname = ?, address = ?, dob = ? WHERE userId = ?");

            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, address);
            ps.setDate(4, dob);
            ps.setInt(5, userId);

            ps.executeUpdate();

            flag = true;

        } catch (SQLException e) {
            System.out.println("Exception occured in the editUser method: " + e.getMessage());
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
        //uiyguig

        return flag;
    }
    public int profilePic(String filePath, int userId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag = false;
        int check = 0;

        try {

            con = getConnection();

            ps = con.prepareStatement("UPDATE userprofile SET profilePic = ? WHERE userId = ?");

            ps.setString(1, filePath);
            ps.setInt(2, userId);

            ps.executeUpdate();

            flag = true;
            check = 1;

        } catch (SQLException e) {
            System.out.println("Exception occured in the profilepic method: " + e.getMessage());
            flag = false;
            check = 2;
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
                flag = false;
                check = 6;
                System.out.println("Exception occured in the finally section of the login() method: " + e.getMessage());
            }
        }
        //uiyguig

        return check;
    }

    public String getUserDetails(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String test = null;
        User thisUser = null;

        try {
            con = getConnection();
            ps = con.prepareStatement("SELECT * FROM userprofile WHERE userId = ?");

            ps.setInt(1, id);

            rs = ps.executeQuery();
            test = rs.getString("fname");
            //thisUser = new User(rs.getInt("userId"), rs.getInt("profileId"), rs.getString("username"), rs.getString("fname"), rs.getString("lname"), rs.getInt("userType"), rs.getInt("active"), rs.getString("email"), rs.getString("address"), rs.getString("dob"), rs.getString("profilePic"));

        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {

            freeConnection(con);
        }

        return test;

    }

    public String detailsTest(int userId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String test = null;

        try {

            con = getConnection();
            String query = "SELECT * FROM userprofile WHERE userId = ?";
            ps = con.prepareStatement(query);

            ps.setInt(1, userId);

            rs = ps.executeQuery();
            test = rs.getString("fname");

        } catch (SQLException e) {
            System.out.println("Exception occured in the editUser method: " + e.getMessage());
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

        return test;
    }

    public boolean addFriend(int userId, int friendId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String test = null;
        boolean check = false;

        try {

            con = getConnection();
            String query = "INSERT into friends(userId, friendId) values(?, ?)";
            ps = con.prepareStatement(query);

            ps.setInt(1, userId);
            ps.setInt(2, friendId);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Exception occured in the editUser method: " + e.getMessage());
            check = true;
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
                check = true;
            }
        }

        return check;
    }

    public int getFriends(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Integer> ids = new ArrayList();
        int count = 0;

        try {
            con = getConnection();
            String query = "SELECT friendId FROM friends WHERE userId = ?";

            ps.setInt(1, id);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                count++;
                ids.add(rs.getInt("friendId"));
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getFriends() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getFriends() method: " + e.getMessage());
            }
        }

        return count;
    }
}
