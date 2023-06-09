package com.ca.security.roles.demo78.persist.repositories;

import com.ca.security.roles.demo78.persist.entities.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    @NotNull Page<User> findAll(@NotNull Pageable pageable);
}
