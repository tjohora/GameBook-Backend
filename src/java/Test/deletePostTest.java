/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Rest.CommentsResource;
import Rest.PostResource;

/**
 *
 * @author TJ
 */
public class deletePostTest {
    public static void main(String[] args)
    {
        CommentsResource ur = new CommentsResource();
        System.out.println(ur.deleteComment(24));
    }
}
