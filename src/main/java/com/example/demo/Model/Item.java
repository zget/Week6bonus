package com.example.demo.Model;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String image;
    private String title;
    private String category;
    private String description;
    private String status="Lost";


    @ManyToOne
    private User auser;

    public Item() {
        this.auser=new User();
    }

    public Item(String image, String title,String category, String description) {
        this.image = image;
        this.title = title;
        this.category=category;
        this.description = description;


    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAuser() {
        return auser;
    }

    public void setAuser(User auser) {
        this.auser = auser;
    }
}
