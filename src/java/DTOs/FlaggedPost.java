
package DTOs;

/**
 *
 * @author andre
 */
public class FlaggedPost {
  private int postID;
    private int userID;
    private int flagPost; 
    
      public FlaggedPost(int postID, int userID, int flagPost) {
        this.postID = postID;
        this.userID = userID;
        this.flagPost = flagPost;
    } 
      
      public FlaggedPost(int postID, int flagPost) {
        this.postID = postID;
        this.flagPost = flagPost ;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getFlagPost() {
        return flagPost;
    }

    public void setFlagPost(int flagPost) {
        this.flagPost = flagPost;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.postID;
        hash = 37 * hash + this.userID;
        hash = 37 * hash + this.flagPost;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FlaggedPost other = (FlaggedPost) obj;
        if (this.postID != other.postID) {
            return false;
        }
        if (this.userID != other.userID) {
            return false;
        }
        if (this.flagPost != other.flagPost) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "flaggedPost{" + "postID=" + postID + ", userID=" + userID + ", flagPost=" + flagPost + '}';
    }
}
