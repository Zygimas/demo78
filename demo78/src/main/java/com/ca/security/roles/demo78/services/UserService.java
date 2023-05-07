package com.ca.security.roles.demo78.services;

import com.ca.security.roles.demo78.persist.entities.User;
import com.ca.security.roles.demo78.transfer.UserDto;
import com.ca.security.roles.demo78.transfer.UserRegDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface UserService {

    Optional<String> getCurrentUsername();

    Optional<User> getCurrentUser();

    Optional<UserDto> findByUsername(String username);

    UserDto getById(Integer id);

    void create(UserRegDto userRegDto);

    Page<UserDto> findAll(Pageable pageable);

    void update(UserDto userDto);

    void disable(Integer id);
}
