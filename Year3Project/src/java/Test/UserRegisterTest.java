/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Rest.UserResource;

/**
 *
 * @author TJ
 */
public class UserRegisterTest {
    public static void main(String[] args)
    {
        String content = "{\"userName\":\"johnjoe123\",\"password\":\"password\",\"email\":\"johnJoe@gmail.com\"}";
        UserResource ur = new UserResource();
        System.out.println(ur.register(content));
    }
    
}
