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
public class getAllComments {
    public static void main(String[] args) {
        CommentsResource cr = new CommentsResource();
        System.out.println(cr.getAllComments());
    }
    
}
