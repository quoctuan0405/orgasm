/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.sql.Date;

/**
 *
 * @author dangd
 */
public class Post {
    int id;
    String avatar;
    String username;
    private Date createdAt;
    String title;
    int cateID;
    String cateName;
    private String content;
    private int category;
    private int authorid;

    public Post() {
    }
    
    public Post(int id, String title, Date createdAt, String content, int category, int authorid) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.content = content;
        this.category = category;
        this.authorid = authorid;
    }

    public Post(int id, String avatar, String username, String title, int cateID, String cateName) {
        this.id = id;
        this.avatar = avatar;
        this.username = username;
        this.title = title;
        this.cateID = cateID;
        this.cateName = cateName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCateID() {
        return cateID;
    }

    public void setCateID(int cateID) {
        this.cateID = cateID;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
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

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
    
    public int getAuthorid() {
        return authorid;
    }

    public void setAuthorid(int authorid) {
        this.authorid = authorid;
    }

    @Override
    public String toString() {
        return "Port{" + "id=" + id + ", avatar=" + avatar + ", username=" + username + ", title=" + title + ", cateID=" + cateID + ", cateName=" + cateName + '}';
    }

}
