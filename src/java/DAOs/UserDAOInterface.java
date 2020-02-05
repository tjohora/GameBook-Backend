 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.User;

/**
 *
 * @author TJ
 */
public interface UserDAOInterface {
    public boolean register(User u);
    public User login (String username, String password);
    //public String passwordRecovery(String link);
}
