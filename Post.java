package com.ganyobicodes.listviewapp;

public class Post {

    public String userId;
    public String title;
    public int id;
    public String body;

    public Post(String userId, String title, int id, String body) {
        this.userId = userId;
        this.title = title;
        this.id = id;
        this.body = body;
    }

    public Post(String title, int id) {
        this.title = title;
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
