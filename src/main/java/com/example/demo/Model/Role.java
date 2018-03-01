package com.example.demo.Model;


import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique=true)
    private String rolename;
    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    private Collection<User> users;

    public Role() {

        this.users=new HashSet<User>();
    }

    public Role(String rolename) {
        this.rolename = rolename;
        this.users=new HashSet<User>();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public String getRolename() {
        return rolename;
    }

    public Collection<User> getUsers() {
        return users;
    }

}
