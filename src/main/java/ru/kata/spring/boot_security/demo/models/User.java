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

    @Column(name="userName")
    private String userName;

    @Column(name="userlastname")
    private String lastName;

    @Column(name="age")
    private long age;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Transient
    transient private String confirmPassword;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name="user_roles", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User() {
    }

    public User(String userName, String lastName, long age, String email, String password) {
        this.userName = userName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.password = password;
    }

    public User(String userName, String lastName, long age, String email, String password, Set<Role> roles) {
        this.userName = userName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

//    public String getUsername() {
//        return userName;
//    }

    public String getLastName() {
        return lastName;
    }

    public long getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

//    @Override
//    public String getPassword() {
//        return password;
//    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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



}
