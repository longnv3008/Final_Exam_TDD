package com.example.bookstore.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private Long id;
    private User user;
    private List<CartItem> items = new ArrayList<>();

    public Cart(){}
    public Cart(User user){
        this.user=user;
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public User getUser(){
        return user;
    }
    public void setUser(User user){
        this.user=user;
    }
    public List<CartItem> getItems(){
        return items;
    }
    public void setItems(List<CartItem> items){
        this.items=items;
    }
}
