/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Administrator
 */
public class Blog {
    private int id;
    private String image;
    private String title;
    private Date createdAt;
    private String content;
    private int category;
    private int authorid;
    private String contentPre;
    
    public Blog() {
    }

    public Blog(int id, String image, String title, Date createdAt, String content, int category, int authorid) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.createdAt = createdAt;
        this.content = content;
        this.category = category;
        this.authorid = authorid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getContentPre() {
        if (this.content == null) return this.content;
        if (this.content.length() < 120)
        {
                return this.content;
        }
        else
        {
            return this.content.substring(0, 120);
        }
    }

    public void setContentPre(String contentPre) {
        this.contentPre = contentPre;
    }
    
}


