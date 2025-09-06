package com.example.bookstore.service;

import com.example.bookstore.model.*;

public class CartService {

    public Cart createCart(User user){
        return new Cart(user);
    }

    /**
     * Add book into cart. If desiredQty <= 0 -> set to 1.
     * If desired qty exceeds stock -> cap at stock.
     * If stock is 0 -> do not add.
     * If item exists, replace quantity with the capped value (simplest behavior).
     * @return actual quantity added to cart
     */
    public int addToCart(Cart cart, Book book, int desiredQty){
        if (book == null || cart == null) throw new IllegalArgumentException("cart/book must not be null");
        int stock = Math.max(0, book.getStockQuantity());
        if (stock == 0) return 0;
        int qty = Math.max(1, desiredQty);
        if (qty > stock) qty = stock;

        // find existing
        CartItem existing = cart.getItems().stream()
                .filter(ci -> ci.getBook().getId().equals(book.getId()))
                .findFirst().orElse(null);
        if (existing == null){
            cart.getItems().add(new CartItem(book, qty));
        }else{
            existing.setQuantity(qty);
        }
        return qty;
    }
}
