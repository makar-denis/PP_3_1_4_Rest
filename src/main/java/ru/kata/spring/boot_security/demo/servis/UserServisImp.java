package ru.kata.spring.boot_security.demo.servis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;

@Transactional
@Service
public class UserServisImp implements UserDetailsService, UserServis { //
//    @PersistenceContext
//    private EntityManager em;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // бин из WebSecurityConfig для кодировки пароля

    @Autowired
    public UserServisImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
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
//        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(passwordEncoder.encode(user.getPassword())); // пришифровании пароля
//        user.setPassword(user.getPassword());
////        Set<Role> roles = new HashSet<>();
////        roles.add(roleRepositiry.getOne(1L));
////        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void change(User user){
        String passwordFromForm = user.getPassword();
        String encodedPasswordFromBase = userRepository.getById(user.getId()).getPassword();
        if (passwordFromForm.equals(encodedPasswordFromBase)) {
            user.setPassword(encodedPasswordFromBase);
        } else {
            if (passwordEncoder.matches(passwordFromForm, encodedPasswordFromBase)) {
                user.setPassword(encodedPasswordFromBase);
            } else {
                user.setPassword(passwordEncoder.encode(passwordFromForm));
            }
        }
        userRepository.save(user);
//        userRepository.saveAndFlush(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> all() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserBiId(long id) {
//        return userRepository.getById(id);
        return userRepository.findById(id).orElse(null);
//        return userRepository.getOne(id);
//        return userRepository.getReferenceById(id);
    }
    @Transactional(readOnly = true)
    @Override
    public User getUserByName(String name) {
        return userRepository.findUserByUserName(name);
    }

}
