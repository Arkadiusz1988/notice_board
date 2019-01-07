package com.example.notice_board.entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Notice {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "notice",fetch = FetchType.EAGER ,cascade = {CascadeType.ALL})
    private List<Comment> comments = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }


    public Notice(String name, String description, User user, List<Comment> comments) {
        this.name = name;
        this.description = description;
        this.user = user;
        this.comments = comments;
    }

    public Notice() {
    }
}
