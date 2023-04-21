package com.example.api.ApiApplication.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private int id;
private String descriptions;
@ManyToOne(fetch = FetchType.LAZY)
@JsonIgnore
private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
@Size(min=9)
    public String getDescriptions() {
        return descriptions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDescriptions(String describtion) {
        this.descriptions = describtion;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", describtion='" + descriptions + '\'' +
                '}';
    }
}

