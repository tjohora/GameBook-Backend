package DAOs;

import DTOs.FlaggedPost;
import java.util.List;
import javax.ws.rs.core.Response;

interface FlaggedPostDAOInterface {
    public boolean reportPost(int postId, int userId, int flagPost); 
    public List<FlaggedPost> getAllFlaggedPosts();
    public List<FlaggedPost> getAllFlaggedPostsByPostID(int postId);

    public String releasePost(int postId);
}
