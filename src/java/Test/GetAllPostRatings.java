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
public class GetAllPostRatings {
    public static void main(String[] args) {
        RatingResource pr = new RatingResource();
        System.out.println(pr.getRatingByPostID(8));
    }
}
