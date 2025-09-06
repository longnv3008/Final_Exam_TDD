package com.example.bookstore.model;

import java.time.LocalDateTime;

public class Review {
    private Long id;
    private User user;
    private Book book;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;

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
    public Book getBook(){
        return book;
    }
    public void setBook(Book book){
        this.book=book;
    }
    public int getRating(){
        return rating;
    }
    public void setRating(int rating){
        this.rating=rating;
    }
    public String getComment(){
        return comment;
    }
    public void setComment(String comment){
        this.comment=comment;
    }
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt=createdAt;
    }
}
