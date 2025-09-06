package com.example.bookstore.service;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.Cart;
import com.example.bookstore.model.CartItem;
import com.example.bookstore.model.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CartServiceTest {


    @Test
    public void addWithinStock_shouldAddRequestedQuantity() {
        CartService srv = new CartService();
        Cart cart = srv.createCart(new User(1L, "long"));
        Book b = new Book(1L, "Clean Code", 10, 100.0);

        int added = srv.addToCart(cart, b, 3);
        assertEquals(1, cart.getItems().size());
        assertEquals(3, cart.getItems().get(0).getQuantity());
        assertEquals(3, added);
    }

    @Test
    public void addSameBookTwice_shouldReplaceWithLastCappedQuantity_andVerifyWithSpyList() {
        CartService srv = new CartService();
        Cart cart = srv.createCart(new User(1L, "u"));

        List<CartItem> spyItems = spy(new ArrayList<>());
        cart.setItems(spyItems);

        Book b = new Book(1L, "A", 5, 10.0);

        int q1 = srv.addToCart(cart, b, 2);
        verify(spyItems, times(1)).add(any(CartItem.class));


        CartItem spyItem = spy(cart.getItems().get(0));
        cart.getItems().set(0, spyItem);

        int q2 = srv.addToCart(cart, b, 10);
        verify(spyItem, times(1)).setQuantity(5);
        verify(spyItems, times(1)).add(any(CartItem.class));

        assertEquals(2, q1);
        assertEquals(5, q2);
        assertEquals(5, cart.getItems().get(0).getQuantity());
    }


    @Test
    public void addExceedingStock_shouldCapAtStock() {
        CartService srv = new CartService();
        Cart cart = srv.createCart(new User(1L, "long"));
        Book b = new Book(1L, "DDD", 2, 120.0);

        int added = srv.addToCart(cart, b, 5);
        assertEquals(2, cart.getItems().get(0).getQuantity());
        assertEquals(2, added);
    }

    @Test
    public void addZeroOrNegative_shouldBecomeOne_ifStockAvailable() {
        CartService srv = new CartService();
        Cart cart = srv.createCart(new User(1L, "u"));
        Book b = new Book(1L, "Refactoring", 5, 90.0);

        int added0 = srv.addToCart(cart, b, 0);
        assertEquals(1, added0);
        assertEquals(1, cart.getItems().get(0).getQuantity());

        int addedNeg = srv.addToCart(cart, b, -4);
        assertEquals(1, addedNeg);
        assertEquals(1, cart.getItems().get(0).getQuantity());
    }

    @Test
    public void addWhenOutOfStock_shouldAddNothing() {
        CartService srv = new CartService();
        Cart cart = srv.createCart(new User(1L, "u"));
        Book b = new Book(1L, "NoStock", 0, 10.0);

        int added = srv.addToCart(cart, b, 2);
        assertEquals(0, added);
        assertTrue(cart.getItems().isEmpty());
    }

    @Test
    public void addWithNegativeStockBook_shouldNotAdd() {
        CartService srv = new CartService();
        Cart cart = srv.createCart(new User(1L, "u"));
        Book b = new Book(1L, "A", -3, 10.0);

        int added = srv.addToCart(cart, b, 1);
        assertEquals(0, added);
        assertTrue(cart.getItems().isEmpty());
    }

    @Test
    public void addNulls_shouldThrow() {
        CartService srv = new CartService();
        Book b = new Book(1L, "A", 3, 10.0);
        assertThrows(IllegalArgumentException.class, () -> srv.addToCart(null, b, 1));
        assertThrows(IllegalArgumentException.class, () -> srv.addToCart(new Cart(new User(1L, "u")), null, 1));
    }
}
