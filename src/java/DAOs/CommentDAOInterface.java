/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Comment;
import java.util.List;

/**
 *
 * @author TJ
 */
public interface CommentDAOInterface {
    public List<Comment> getAllComments();
    public List<Comment> getCommentsOfUser(int userId);
    public List<Comment> getCommentsOfPost(int postId);
    public boolean makeComment(int userId, int postId, String content);
    public boolean deleteComment(int commentID);
    public boolean updateComment(int commentID, String content);
    
}
