package ru.kata.spring.rest.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.rest.demo.models.User;
import ru.kata.spring.rest.demo.repository.UserRepository;

import java.util.List;

@Transactional
@Service
public class UserServiceImp implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // бин из WebSecurityConfig для кодировки пароля

    @Autowired
    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userRepository.findUserByUserName(username);
        user.getAuthorities().size(); // добавил
        if (user==null){
            throw new UsernameNotFoundException("Пользватель не найден");
        }  // проверка наличия пользователя
        return user;
    }

    @Override
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // пришифровании пароля
        userRepository.save(user);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void change(User user){
        String passwordFromForm = user.getPassword();
        user.setPassword(passwordEncoder.encode(passwordFromForm));
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> all() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(long id) {
        return userRepository.findUserById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserByName(String name) {
        return userRepository.findUserByUserName(name);
    }

}
