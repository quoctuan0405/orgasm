/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.sql.Date;

/**
 *
 * @author LAPTOP D&N
 */
public class PostComment {

    int id;
    int authorId;
    int postId;
    Date createdAt;
    String content;
    String avatar;
    
    public PostComment() {
    }

    public PostComment(int id, int authorId, int postId, Date createdAt, String content, String avatar) {
        this.id = id;
        this.authorId = authorId;
        this.postId = postId;
        this.createdAt = createdAt;
        this.content = content;
        this.avatar = avatar;
    }    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "PostComment{" + "id=" + id + ", authorId=" + authorId + ", postId=" + postId + ", createdAt=" + createdAt + ", content=" + content + ", avatar=" + avatar + '}';
    }
    

}
