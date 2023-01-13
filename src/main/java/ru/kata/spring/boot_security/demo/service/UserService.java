package ru.kata.spring.boot_security.demo.service;





import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Collection;
import java.util.List;

public interface UserService extends UserDetailsService {
    void add(User user);

    User getById(int id);

    void delete(int id);

    void edit( Integer id, User user);

    public List<User> getAllUsers();

   public User showUser(Integer id);


    Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles);
}



