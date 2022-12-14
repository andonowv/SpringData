package com.example.dtoEx.repository;

import com.example.dtoEx.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
    Optional <User> findByEmailAndFullName(String email, String password);
}
