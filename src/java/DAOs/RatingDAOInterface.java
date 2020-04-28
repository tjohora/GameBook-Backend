/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Rating;
import java.util.List;

/**
 *
 * @author TJ
 */
public interface RatingDAOInterface {
    public boolean updateRating(int postId, int userId, int selectedRating);
    public List<Rating> getRatingByPostID(int postId);
    public List<Rating> getRatingOfUser(int userId);
    public List<Rating> getAllPostRatings();
}
