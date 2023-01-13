package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Propagation;
import ru.kata.spring.boot_security.demo.dao.UserRepository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImp implements UserService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void add(User user) {
        repository.save(user);

    }



    @Override
    public User getById(int id) {
        return repository.getById(id);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public void edit(Integer id, User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userEdit = showUser(id);
        userEdit.setUsername(user.getUsername());
        userEdit.setEmail(user.getEmail());
        userEdit.setSurname(user.getSurname());
        userEdit.setId(user.getId());
        userEdit.setRoles(user.getRoles());
        repository.save(userEdit);
    }


    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User showUser(Integer id) {
       return repository.findById(id).get();
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findUserByUsername(username);


        if (user == null) {
            throw new UsernameNotFoundException("User not found!");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }
    @Override
    public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }
}
//    @Override
//    public List<Role> getAllRoles() {
//        return dao.getAllRolls();
//    }

//



