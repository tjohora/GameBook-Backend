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
public class TestSearch {
    public static void main(String[] args)
    {
        String content = "test";
        PostResource ur = new PostResource();
        System.out.println(ur.getPostBySearch(content));
    }
}
