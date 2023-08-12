package com.example.traveler.model.dto;

public class CommentDTO {
    private String content;

    // Constructors
    public CommentDTO() {
    }

    public CommentDTO(String content) {
        this.content = content;
    }

    // Getters and Setters
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
