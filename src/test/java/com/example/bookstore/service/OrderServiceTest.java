package com.example.bookstore.service;

import com.example.bookstore.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {


    @Test
    public void checkout_shouldReduceStock_andClearCart_andVerifySetStock_withSpyBook() {
        CartService cartService = new CartService();
        OrderService orderService = new OrderService();

        Cart cart = cartService.createCart(new User(1L, "long"));


        Book b1 = spy(new Book(1L, "A", 5, 10.0));
        Book b2 = spy(new Book(2L, "B", 2, 20.0));

        cartService.addToCart(cart, b1, 3);
        cartService.addToCart(cart, b2, 5);

        List<Order> orders = orderService.checkout(cart);

        assertEquals(0, cart.getItems().size());
        assertEquals(2, orders.size());
        assertEquals(2, b1.getStockQuantity());
        assertEquals(0, b2.getStockQuantity());


        verify(b1, atLeastOnce()).setStockQuantity(2);
        verify(b2, atLeastOnce()).setStockQuantity(0);
    }

    @Test
    public void cancel_shouldRestoreStock_andVerifySetter() {
        OrderService orderService = new OrderService();
        Book b = spy(new Book(1L, "A", 1, 10.0));
        Order o = new Order(b, 1);

        orderService.cancel(o);
        assertEquals(2, b.getStockQuantity());
        verify(b, atLeastOnce()).setStockQuantity(2);
    }

    @Test
    public void checkout_multipleItems_then_cancel_one_order_should_restore_only_that_book() {
        CartService cartService = new CartService();
        OrderService orderService = new OrderService();
        Cart cart = cartService.createCart(new User(1L, "u"));

        Book b1 = new Book(1L, "A", 4, 10.0);
        Book b2 = new Book(2L, "B", 3, 12.0);

        cartService.addToCart(cart, b1, 3);
        cartService.addToCart(cart, b2, 5);

        List<Order> orders = orderService.checkout(cart);
        assertEquals(0, cart.getItems().size());
        assertEquals(1, b1.getStockQuantity());
        assertEquals(0, b2.getStockQuantity());


        Order orderB2 = orders.stream().filter(o -> o.getBook().getId() == 2L).findFirst().get();
        orderService.cancel(orderB2);

        assertEquals(1, b1.getStockQuantity());
        assertEquals(3, b2.getStockQuantity());
    }


    @Test
    public void checkout_null_cart_shouldThrow() {
        OrderService svc = new OrderService();
        assertThrows(IllegalArgumentException.class, () -> svc.checkout(null));
    }

    @Test
    public void cancel_null_order_shouldThrow() {
        OrderService svc = new OrderService();
        assertThrows(IllegalArgumentException.class, () -> svc.cancel(null));
    }
}
