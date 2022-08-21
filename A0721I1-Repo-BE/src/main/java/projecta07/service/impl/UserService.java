package projecta07.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projecta07.model.Role;
import projecta07.model.User;
import projecta07.repository.UserRepository;
import projecta07.service.IUserService;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.*;

@Service
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {

        return userRepository.save(user);
    }

    @Override
    public Iterable<User> saveAll(Iterable<User> users) {
        return userRepository.saveAll(users);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Iterable<User> findAllById(Iterable<Long> iterable) {
        return userRepository.findAllById(iterable);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void deleteAll(Iterable<? extends User> iterable) {
        userRepository.deleteAll(iterable);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);

        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getNameRole()));
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), grantedAuthorities
        );
    }

    @Override
    @Query("SELECT u.id FROM User u WHERE u.username = ?1")
    public Integer getUserIdByUsername(String username) {
        return userRepository.getUserIdByUsername(username);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public Boolean checkExistUser(String username) {
        return userRepository.existsUsersByUsername(username);
    }
}
