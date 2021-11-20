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
public class Ticket {
    private int id;
    private int authorId;
    private Date createdAt;
    private String content;
    private String title;
    private String avatar;
    private String username;

    public Ticket() {
    }

    public Ticket(int id, int authorId, Date createdAt, String content, String title, String avatar, String username) {
        this.id = id;
        this.authorId = authorId;
        this.createdAt = createdAt;
        this.content = content;
        this.title = title;
        this.avatar = avatar;
        this.username = username;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Override
    public String toString() {
        return "Ticket{" + "id=" + id + ", authorId=" + authorId + ", createdAt=" + createdAt + ", content=" + content + ", title=" + title + ", avatar=" + avatar + ", username=" + username + '}';
    }

}
