package com.spring.boot.security.jwtsecurity.repository;

import com.spring.boot.security.jwtsecurity.model.Prize;
import com.spring.boot.security.jwtsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);

}
