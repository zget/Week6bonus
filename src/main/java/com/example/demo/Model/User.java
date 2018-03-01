package com.example.demo.Model;


import com.example.demo.Model.Role;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "username")
    private String userName;
    @Column(name="password")
    private String password;
    @Column(name="email")
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "enabled")
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

        @OneToMany(mappedBy = "auser")
        private Set<Item> items;

    public User(String email, String password, String firstName, String lastName, boolean enabled, String userName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enabled = enabled;
        this.userName = userName;
        this.roles= new HashSet<Role>();
        this.items= new HashSet<Item>();

    }



    public User() {
        this.roles= new HashSet<Role>();
        this.items= new HashSet<Item>();
    }



    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getUserName() {
        return userName;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Set<Item> getItems() { return items;  }

    public void setItems(Set<Item> items) {  this.items = items; }

    public void addRole(Role r){
        this.roles.add(r);
    }

//    public void additem(Item i){
//        this.items.add(i);
//    }

}
