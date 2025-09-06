package com.example.bookstore.service;

import com.example.bookstore.model.*;

import java.util.ArrayList;
import java.util.List;

public class OrderService {

    public List<Order> checkout(Cart cart){
        if (cart == null) throw new IllegalArgumentException("cart must not be null");
        List<Order> orders = new ArrayList<>();
        for (CartItem item : cart.getItems()){
            Book book = item.getBook();
            int qty = item.getQuantity();
            // reduce stock by qty (assume qty already capped when added)
            int remain = book.getStockQuantity() - qty;
            if (remain < 0) remain = 0;
            book.setStockQuantity(remain);
            orders.add(new Order(book, qty));
        }
        // clear cart
        cart.getItems().clear();
        return orders;
    }

    public void cancel(Order order){
        if (order == null) throw new IllegalArgumentException("order must not be null");
        Book book = order.getBook();
        book.setStockQuantity(book.getStockQuantity() + order.getQuantity());
    }
}
