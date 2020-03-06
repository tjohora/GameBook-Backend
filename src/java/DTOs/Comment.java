/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

/**
 *
 * @author TJ
 */
public class Comment {
    private int userId;
    private int postID;
    private int commentID;
    private String userName;
    private String content;
    private String commentDate;
    private int active;

    public Comment(int userId, int postID, int commentID, String userName, String content, String commentDate, int active) {
        this.userId = userId;
        this.postID = postID;
        this.commentID = commentID;
        this.userName = userName;
        this.content = content;
        this.commentDate = commentDate;
        this.active = active;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.userId;
        hash = 37 * hash + this.postID;
        hash = 37 * hash + this.commentID;
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
        final Comment other = (Comment) obj;
        if (this.userId != other.userId) {
            return false;
        }
        if (this.postID != other.postID) {
            return false;
        }
        if (this.commentID != other.commentID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Comment{" + "userId=" + userId + ", postID=" + postID + ", commentID=" + commentID + ", userName=" + userName + ", content=" + content + ", commentDate=" + commentDate + ", active=" + active + '}';
    }

    

    
    
    
}
