 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.User;
import java.util.List;

/**
 *
 * @author TJ
 */
public interface UserDAOInterface {
    public boolean register(String userName, String password, String email);
    public User login (String username, String password);
    //public String passwordRecovery(String link);
    public List<User> getAllUsers();
}
