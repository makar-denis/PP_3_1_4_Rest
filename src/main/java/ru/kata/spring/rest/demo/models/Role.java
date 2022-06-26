package ru.kata.spring.rest.demo.models;

import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private String role;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role() {
    }

    public Role(String role) {
        this.role=role;
    }
//
    public Role(Long id, String role) {
        this.id = id;
        this.role = role;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String role) {
        this.role = role;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return role;
    }

    public Set<User> getUsers() {
        return users;
    }

    @Override
    public String getAuthority() {
//        return null;
        return getName();
    }

}
