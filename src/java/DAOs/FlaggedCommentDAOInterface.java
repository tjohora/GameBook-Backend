package DAOs;

import DTOs.FlaggedComment;
import java.util.List;

interface FlaggedCommentDAOInterface {
    public boolean reportComment(int commentId, int userId, int flagComment);
    public List<FlaggedComment> getAllFlaggedComments();
    public String releaseComment(int postId);
}
