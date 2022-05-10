package ru.kata.spring.boot_security.demo.models;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import javax.persistence.*;
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.Set;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

//@Data
@Entity
@Table(name="users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Transient
    transient private String confirmPassword;

    @Column(name="email")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name="user_roles", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password, String email, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
//        return false;
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
//        return false;
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
//        return false;
        return true;
    }

    @Override
    public boolean isEnabled() {
//        return false;
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
        return  getRoles();
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
