package ru.kata.spring.rest.demo.service;

import ru.kata.spring.rest.demo.models.User;

import java.util.List;

public interface UserService {
    void add(User user);
    void delete(long id);
    void change(User user);
    User getUserById(long id);
    User getUserByName(String name);
    List<User> all();
}
