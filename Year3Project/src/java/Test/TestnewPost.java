/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Rest.PostResource;

/**
 *
 * @author TJ
 */
public class TestnewPost {
    public static void main(String[] args)
    {
        String content = "{\"userId\":25,\"postHeader\":\"I posted this\",\"postContent\":\"From the form\",\"media\":1}";
        PostResource ur = new PostResource();
        System.out.println(ur.makePost(content));
    }
}
