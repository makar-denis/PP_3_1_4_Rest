package ru.kata.spring.rest.demo.servis;

import ru.kata.spring.rest.demo.models.User;

import java.util.List;

public interface UserServis {
    void add(User user);
    void delete(long id);
    void change(User user);
    User getUserBiId(long id);
    User getUserByName(String name);
    List<User> all();
}
