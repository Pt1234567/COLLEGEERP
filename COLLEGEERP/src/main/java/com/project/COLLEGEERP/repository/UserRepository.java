package com.project.COLLEGEERP.repository;


import com.project.COLLEGEERP.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserId(String userId);
}
