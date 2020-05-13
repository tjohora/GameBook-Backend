/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import DAOs.UserDAO;
import Rest.UserResource;

/**
 *
 * @author Tom
 */
public class friendTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
//        
//        UserResource ur = new UserResource();
//        
//        
UserDAO u = new UserDAO("projectdb");
    System.out.println(u.addFriend(57,62));
    }
    
}
