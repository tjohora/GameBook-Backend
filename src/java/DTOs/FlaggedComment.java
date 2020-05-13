package DTOs;


public class FlaggedComment {
     private int commentID;
    private int userID;
    private int flagComment; 
    
      public FlaggedComment(int commentID, int userID, int flagComment) {
        this.commentID = commentID;
        this.userID = userID;
        this.flagComment = flagComment;
    } 
      
      public FlaggedComment(int commentID, int flagComment) {
        this.commentID = commentID;
        this.flagComment = flagComment ;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getFlagComment() {
        return flagComment;
    }

    public void setFlagComment(int flagComment) {
        this.flagComment = flagComment;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.commentID;
        hash = 37 * hash + this.userID;
        hash = 37 * hash + this.flagComment;
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
        final FlaggedComment other = (FlaggedComment) obj;
        if (this.commentID != other.commentID) {
            return false;
        }
        if (this.userID != other.userID) {
            return false;
        }
        if (this.flagComment != other.flagComment) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "flaggedComment{" + "commentID=" + commentID + ", userID=" + userID + ", flagComment=" + flagComment + '}';
    } 
}
