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
public class Rating {
    private int postID;
    private int userID;
    private int selectedRating;

    public Rating(int postID, int userID, int selectedRating) {
        this.postID = postID;
        this.userID = userID;
        this.selectedRating = selectedRating;
    }

    public Rating(int postID, int selectedRating) {
        this.postID = postID;
        this.selectedRating = selectedRating;
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

    public int getSelectedRating() {
        return selectedRating;
    }

    public void setSelectedRating(int selectedRating) {
        this.selectedRating = selectedRating;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.postID;
        hash = 37 * hash + this.userID;
        hash = 37 * hash + this.selectedRating;
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
        final Rating other = (Rating) obj;
        if (this.postID != other.postID) {
            return false;
        }
        if (this.userID != other.userID) {
            return false;
        }
        if (this.selectedRating != other.selectedRating) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rating{" + "postID=" + postID + ", userID=" + userID + ", selectedRating=" + selectedRating + '}';
    }
    
}
