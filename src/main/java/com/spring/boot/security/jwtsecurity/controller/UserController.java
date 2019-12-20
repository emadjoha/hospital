package com.spring.boot.security.jwtsecurity.controller;


import com.spring.boot.security.jwtsecurity.exceptions.NotFoundException;
import com.spring.boot.security.jwtsecurity.exceptions.ValidationException;
import com.spring.boot.security.jwtsecurity.model.Role;
import com.spring.boot.security.jwtsecurity.model.User;
import com.spring.boot.security.jwtsecurity.repository.RoleRepository;
import com.spring.boot.security.jwtsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserRepository userInfoRepository;
    @Autowired
    private RoleRepository roleRepository;


    @PostMapping("/user")
    public Boolean create(@RequestBody User body) throws NoSuchAlgorithmException {
        String username = body.getUsername();
        if (userInfoRepository.existsByUsername(username)){

            throw new ValidationException("Username already existed");

        }

        String password = body.getPassword();
        String encodedPassword = new BCryptPasswordEncoder().encode(password);
        String fullName = body.getFullName();
        Set<Role> roles = new HashSet<Role>();
        body.getRoles().stream().forEach(t->{
            if(this.roleRepository.findRoleByName(t.getName()) != null){
                roles.add(this.roleRepository.findRoleByName(t.getName()));
            }else{
                roles.add(t);
            }
        });

        String phone =body.getPhone();
        userInfoRepository.save(new User(username, encodedPassword, fullName,phone,roles));
        return true;
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id")Long id){
        if(userInfoRepository.findById(id).isPresent()){
            userInfoRepository.deleteById(id);
            return ResponseEntity.ok(true);
        }else{
            throw new NotFoundException("this user not found with id : "+id);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> index(){
        return ResponseEntity.ok(this.userInfoRepository.findAll());
    }






}
