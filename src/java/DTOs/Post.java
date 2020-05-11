/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import java.util.Objects;

/**
 *
 * @author TJ
 */
public class Post {
    private int postId;
    private int userId;
    private String userName;
    private String postHeader;
    private String postContent;
    private String postDate;
    private String media;
    private int active;
    private int flagged;

    public Post(int postId, int userId, String userName, String postHeader, String postContent, String postDate, String media, int active, int flagged) {
        this.postId = postId;
        this.userId = userId;
        this.userName = userName;
        this.postHeader = postHeader;
        this.postContent = postContent;
        this.postDate = postDate;
        this.media = media;
        this.active = active;
        this.flagged = flagged;
    }

    public Post() {
        
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPostHeader() {
        return postHeader;
    }

    public void setPostHeader(String postHeader) {
        this.postHeader = postHeader;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
    
    public int getFlagged() {
        return active;
    }

    public void setFlagged(int active) {
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
        int hash = 5;
        hash = 67 * hash + this.postId;
        hash = 67 * hash + this.userId;
        hash = 67 * hash + Objects.hashCode(this.postHeader);
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
        final Post other = (Post) obj;
        if (this.postId != other.postId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        if (!Objects.equals(this.postHeader, other.postHeader)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Post{" + "postId=" + postId + ", userId=" + userId + ", userName=" + userName + ", postHeader=" + postHeader + ", postContent=" + postContent + ", postDate=" + postDate + ", media=" + media + ", active=" + active + '}';
    }
    
}
