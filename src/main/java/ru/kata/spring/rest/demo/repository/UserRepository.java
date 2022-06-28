package ru.kata.spring.rest.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.rest.demo.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findUserByUserName(final String name);
    User findUserById(long id);
}
