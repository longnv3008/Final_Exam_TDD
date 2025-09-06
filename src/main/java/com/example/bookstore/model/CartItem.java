package com.example.bookstore.model;

public class CartItem {
    private Long id;
    private Book book;
    private int quantity;

    public CartItem(){}
    public CartItem(Book book, int quantity){
        this.book=book; this.quantity=quantity;
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public Book getBook(){
        return book;
    }
    public void setBook(Book book){
        this.book=book;
    }
    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity){
        this.quantity=quantity;
    }
}
