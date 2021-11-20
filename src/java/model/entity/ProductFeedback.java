/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

/**
 *
 * @author Administrator
 */
public class ProductFeedback {
    int id;
    int orderProductId;
    String createdAt;
    int rating;
    String description;
    
    String avatar;

    public ProductFeedback(int id, int orderProductId, String createdAt, int rating, String description, String avatar) {
        this.id = id;
        this.orderProductId = orderProductId;
        this.createdAt = createdAt;
        this.rating = rating;
        this.description = description;
        this.avatar = avatar;
    }

    public ProductFeedback() {
    }

    public ProductFeedback(int id, int orderProductId, String createdAt, int rating, String description) {
        this.id = id;
        this.orderProductId = orderProductId;
        this.createdAt = createdAt;
        this.rating = rating;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(int orderProductId) {
        this.orderProductId = orderProductId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
}
