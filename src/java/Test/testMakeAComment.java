/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Rest.CommentsResource;

/**
 *
 * @author TJ
 */
public class testMakeAComment {
    public static void main(String[] args)
    {
        String content = "{\"postId\":5,\"userId\": 25,\"content\":\"Test content!\"}";
        CommentsResource ur = new CommentsResource();
        System.out.println(ur.makeComment(content));
    }
}
