/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.ArrayList;
import java.util.List;
import model.entity.ProductInCart;

/**
 *
 * @author Administrator
 */
public class Cart {

    private List<ProductInCart> products;

    public Cart() {
        products = new ArrayList<>();
    }

    public void setProducts(List<ProductInCart> products) {
        this.products = products;
    }

    public List<ProductInCart> getProducts() {
        return products;
    }

    public int getQuantityById(int id) {
        return getProductInCartById(id).getQuantity();
    }

    private ProductInCart getProductInCartById(int id) {
        for (ProductInCart i : products) {
            if (i.getProduct().getId() == id) {
                return i;
            }
        }
        return null;
    }

    public void addToCart(ProductInCart t) {
        if (getProductInCartById(t.getProduct().getId()) != null) {
            ProductInCart m = getProductInCartById(t.getProduct().getId());
            m.setQuantity(m.getQuantity() + t.getQuantity());
        } else {
            products.add(t);
        }
    }

    public void removeFromCart(int id) {
        if (getProductInCartById(id) != null) {
            products.remove(getProductInCartById(id));
        }
    }

}
