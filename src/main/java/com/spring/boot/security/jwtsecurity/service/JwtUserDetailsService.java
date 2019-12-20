package com.spring.boot.security.jwtsecurity.service;

import com.spring.boot.security.jwtsecurity.model.User;
import com.spring.boot.security.jwtsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class JwtUserDetailsService implements UserDetailsService  ,UserService{

    @Autowired
    private UserRepository userInfoRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userInfoRepository.findByUsername(username);
        if (user.get() == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(),
                getAuthority(user.get()));
    }
    private Set getAuthority(User user) {
        Set authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }


    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userInfoRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }


    @Override
    public void delete(Long id) {
        userInfoRepository.deleteById(id);
    }

    @Override
    public User findOne(String username) {
        return userInfoRepository.findByUsername(username).get();
    }



    @Override
    public User findById(Long id) {
        return userInfoRepository.findById(id).get();
    }
//

}
