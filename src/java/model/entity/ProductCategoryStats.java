package model.entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class ProductCategoryStats {
    private String name;
    private int totalItems;

    public ProductCategoryStats() {
        // Comment
    }

    public ProductCategoryStats(String name, int totalItems) {
        this.name = name;
        this.totalItems = totalItems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }
}