/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Post;
import java.util.List;

/**
 *
 * @author TJ
 */
public interface PostDAOInterface 
{
    public List<Post> getAllPosts();
    public List<Post> getOnePost(int postId);
    public List<Post> getPostsByUser(int userID);
    public boolean makeAPost(Post p);
    public boolean deleteAPost(int postId);
    public boolean updateAPost(int postId, String title, String content);
}
