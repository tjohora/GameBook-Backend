/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Rest.RatingResource;

/**
 *
 * @author TJ
 */
public class RatingUpdateTest {
    public static void main(String[] args)
    {
        String content = "{\"postId\":8,\"userId\":34,\"selectedRating\":0}";
        RatingResource ur = new RatingResource();
        System.out.println(ur.updateRating(content));
    }
}
