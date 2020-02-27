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
        String content = "{\"userName\":\"qwerty\",\"password\":\"password\",\"email\":\"johnqwe3@gmail.com\"}";
        UserResource ur = new UserResource();
        System.out.println(ur.register(content));
    }
    
}
