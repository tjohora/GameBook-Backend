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
public class PostGetAllTest {
    public static void main(String[] args) {
        PostResource pr = new PostResource();
        System.out.println(pr.getPostsByUser(8));
    }
}
