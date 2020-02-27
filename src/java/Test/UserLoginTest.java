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
 * Test
 * erwer
 */
public class UserLoginTest {
    public static void main(String[] args) {
        String content = "{\"userName\":\"johnjoe2\",\"password\":\"password\"}";
        UserResource ur = new UserResource();
        System.out.println(ur.login(content));
    }
}
