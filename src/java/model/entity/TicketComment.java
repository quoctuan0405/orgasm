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
public class TicketComment {
    private int id;
    private int authorId;
    private Date createdAt;
    private int ticketId;
    private String content;
    private String avatar;

    public TicketComment() {
    }

    public TicketComment(int id, int authorId, Date createdAt, int ticketId, String content, String avatar) {
        this.id = id;
        this.authorId = authorId;
        this.createdAt = createdAt;
        this.ticketId = ticketId;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
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
        return "TicketComment{" + "id=" + id + ", authorId=" + authorId + ", createdAt=" + createdAt + ", ticketId=" + ticketId + ", content=" + content + ", avatar=" + avatar + '}';
    }
    
    
}
