/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author LAPTOP D&N
 */
public class Comment {
    int id;
    int authorId;
    int blogId;
    Date createdAt;
    String content; 
    String avatar;
    

    public Comment() {
        // comment
    }

    public Comment(int id, int authorId, int blogId, Date createdAt, String content, String avatar) {
        this.id = id;
        this.authorId = authorId;
        this.blogId = blogId;
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

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
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
        return "Comment{" + "id=" + id + ", authorId=" + authorId + ", blogId=" + blogId + ", createdAt=" + createdAt + ", content=" + content + ", avatar=" + avatar + '}';
    }
      

    
    
    
        
}
