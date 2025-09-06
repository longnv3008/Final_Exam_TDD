package com.example.bookstore.model;

import com.example.bookstore.model.enums.PaymentMethod;
import com.example.bookstore.model.enums.PaymentStatus;
import com.example.bookstore.model.enums.UserRole;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ModelPojoTest {

    @Test
    public void user_getters_setters() {
        User u = new User();
        u.setId(10L);
        u.setUsername("alice");
        u.setPassword("secret");
        u.setEmail("a@b.com");
        u.setFullName("Alice A");
        u.setAddress("HN");
        u.setPhone("0123");
        u.setRole(UserRole.CUSTOMER);

        assertEquals(10L, u.getId());
        assertEquals("alice", u.getUsername());
        assertEquals("secret", u.getPassword());
        assertEquals("a@b.com", u.getEmail());
        assertEquals("Alice A", u.getFullName());
        assertEquals("HN", u.getAddress());
        assertEquals("0123", u.getPhone());
        assertEquals(UserRole.CUSTOMER, u.getRole());
    }

    @Test
    public void category_and_book_linking() {
        Category c = new Category();
        c.setId(1L);
        c.setName("Novel");
        c.setDescription("Desc");

        Book b = new Book();
        b.setId(2L);
        b.setTitle("Title");
        b.setAuthor("Author");
        b.setPublisher("Publisher");
        b.setIsbn("ISBN");
        b.setPrice(12.5);
        b.setStockQuantity(7);
        b.setDescription("D");
        b.setCategory(c);

        assertEquals(1L, b.getCategory().getId());
        assertEquals("Novel", b.getCategory().getName());
        assertEquals(7, b.getStockQuantity());
        assertEquals(12.5, b.getPrice());
        assertEquals("ISBN", b.getIsbn());
    }

    @Test
    public void cart_cartItem_relationships() {
        User u = new User(1L, "bob");
        Cart cart = new Cart(u);

        Book book = new Book(9L, "B", 3, 10.0);
        CartItem item = new CartItem(book, 2);
        cart.getItems().add(item);

        assertEquals(1, cart.getItems().size());
        assertEquals(2, cart.getItems().get(0).getQuantity());
        assertEquals("B", cart.getItems().get(0).getBook().getTitle());
    }

    @Test
    public void order_payment_review_pojos() {
        Book b = new Book(3L, "C", 5, 20.0);
        Order o = new Order(b, 2);

        Payment p = new Payment();
        p.setOrder(o);
        p.setAmount(40.0);

        Review r = new Review();
        r.setBook(b);
        r.setUser(new User(4L, "rater"));
        r.setRating(5);
        r.setComment("good");
        r.setCreatedAt(LocalDateTime.now());

        assertEquals(2, o.getQuantity());
        assertEquals(40.0, p.getAmount());
        assertEquals("good", r.getComment());
        assertEquals("C", r.getBook().getTitle());
        assertNotNull(r.getCreatedAt());
    }

    @Test
    public void cart_all_setters_getters_and_null_list() {
        User u = new User(2L, "u2");
        Cart c = new Cart();
        c.setId(99L);
        c.setUser(u);
        assertEquals(99L, c.getId());
        assertEquals(u, c.getUser());


        c.setItems(null);
        assertNull(c.getItems());


        List<CartItem> items = new ArrayList<>();
        Book b = new Book(7L, "Title7", 3, 5.5);
        CartItem ci = new CartItem(b, 2);
        items.add(ci);
        c.setItems(items);
        assertSame(items, c.getItems());
        assertEquals(1, c.getItems().size());
        assertEquals("Title7", c.getItems().get(0).getBook().getTitle());
    }

    @Test
    public void cartItem_all_setters_getters() {
        Book b = new Book(8L, "T8", 9, 12.0);
        CartItem ci = new CartItem();
        ci.setId(88L);
        ci.setBook(b);
        ci.setQuantity(4);

        assertEquals(88L, ci.getId());
        assertEquals(b, ci.getBook());
        assertEquals(4, ci.getQuantity());

        ci.setQuantity(1);
        assertEquals(1, ci.getQuantity());
    }

    @Test
    public void order_all_setters_getters() {
        Book b = new Book(9L, "T9", 4, 10.0);
        Order o = new Order();
        o.setId(77L);
        o.setBook(b);
        o.setQuantity(3);

        assertEquals(77L, o.getId());
        assertEquals(b, o.getBook());
        assertEquals(3, o.getQuantity());
    }

    @Test
    public void payment_all_setters_getters_and_status_flow() {
        Book b = new Book(10L, "PayBook", 2, 30.0);
        Order o = new Order(b, 2);
        Payment p = new Payment();

        p.setId(123L);
        p.setOrder(o);
        p.setMethod(PaymentMethod.CARD);
        p.setAmount(60.0);
        p.setStatus(PaymentStatus.PENDING);

        assertEquals(123L, p.getId());
        assertEquals(o, p.getOrder());
        assertEquals(PaymentMethod.CARD, p.getMethod());
        assertEquals(60.0, p.getAmount());
        assertEquals(PaymentStatus.PENDING, p.getStatus());

        p.setMethod(PaymentMethod.CASH);
        p.setStatus(PaymentStatus.PAID);
        assertEquals(PaymentMethod.CASH, p.getMethod());
        assertEquals(PaymentStatus.PAID, p.getStatus());
    }

    @Test
    public void category_all_setters_getters() {
        Category c = new Category();
        c.setId(5L);
        c.setName("Cat");
        c.setDescription("Desc");
        assertEquals(5L, c.getId());
        assertEquals("Cat", c.getName());
        assertEquals("Desc", c.getDescription());
    }

    @Test
    public void review_all_setters_getters() {
        Review r = new Review();
        r.setId(6L);
        r.setUser(new User(3L, "reviewer"));
        r.setBook(new Book(11L, "RBook", 1, 9.0));
        r.setRating(4);
        r.setComment("nice");
        LocalDateTime now = LocalDateTime.now();
        r.setCreatedAt(now);

        assertEquals(6L, r.getId());
        assertEquals("reviewer", r.getUser().getUsername());
        assertEquals("RBook", r.getBook().getTitle());
        assertEquals(4, r.getRating());
        assertEquals("nice", r.getComment());
        assertEquals(now, r.getCreatedAt());
    }

    @Test
    public void book_all_setters_getters_and_mutations() {
        Category cat = new Category(1L, "Novel");
        Book b = new Book();
        b.setId(1L);
        b.setTitle("X");
        b.setAuthor("Y");
        b.setPublisher("Z");
        b.setIsbn("ISBN-X");
        b.setPrice(15.5);
        b.setStockQuantity(10);
        b.setDescription("Desc");
        b.setCategory(cat);

        assertEquals(1L, b.getId());
        assertEquals("X", b.getTitle());
        assertEquals("Y", b.getAuthor());
        assertEquals("Z", b.getPublisher());
        assertEquals("ISBN-X", b.getIsbn());
        assertEquals(15.5, b.getPrice());
        assertEquals(10, b.getStockQuantity());
        assertEquals("Desc", b.getDescription());
        assertEquals(cat, b.getCategory());

        b.setStockQuantity(3);
        b.setPrice(19.99);
        assertEquals(3, b.getStockQuantity());
        assertEquals(19.99, b.getPrice());
    }

    @Test
    public void user_constructor_minimal_and_role_change() {
        User u = new User(1L, "alice");
        assertEquals(1L, u.getId());
        assertEquals("alice", u.getUsername());
        u.setRole(UserRole.ADMIN);
        assertEquals(UserRole.ADMIN, u.getRole());
    }
}
