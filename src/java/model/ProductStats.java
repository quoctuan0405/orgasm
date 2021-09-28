/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Admin
 */
public class ProductStats {
    private int id;
    private String name;
    private double price;
    private int sold;
    private int remained;
    private double total;

    public ProductStats() {}

    public ProductStats(int id, String name, double price, int sold, int remained, double total) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.sold = sold;
        this.remained = remained;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public int getRemained() {
        return remained;
    }

    public void setRemained(int remained) {
        this.remained = remained;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
}