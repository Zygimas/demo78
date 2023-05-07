package com.ca.security.roles.demo78.services.Impl;

import com.ca.security.roles.demo78.persist.entities.Role;
import com.ca.security.roles.demo78.persist.entities.User;
import com.ca.security.roles.demo78.persist.repositories.UserRepository;
import com.ca.security.roles.demo78.services.UserService;
import com.ca.security.roles.demo78.transfer.UserDto;
import com.ca.security.roles.demo78.transfer.UserRegDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<String> getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }
        return Optional.of(auth.getName());
    }

    @Override
    public Optional<User> getCurrentUser() {
        Optional<String> optionalUsername = getCurrentUsername();
        if (optionalUsername.isPresent()) {
            return userRepository.findByUsername(optionalUsername.get());
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserDto> findByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return Optional.of(new UserDto(
                    user.getId(),
                    user.getUsername(),
                    user.getRole(),
                    user.isActive()));
        }
        return Optional.empty();
    }

    @Override
    public UserDto getById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("No user with such id!");
        }
        User user = optionalUser.get();
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getRole(),
                user.isActive());
    }

    @Override
    public void create(UserRegDto userRegDto) {
        String encryptedPassword = passwordEncoder.encode(userRegDto.getPassword());
        User newUser = new User();
        newUser.setUsername(userRegDto.getUsername());
        newUser.setEncryptedPassword(encryptedPassword);
        newUser.setRole(Role.USER);
        newUser.setActive(true);
        userRepository.save(newUser);
    }

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        Page<User> usersPage = userRepository.findAll(pageable);
        List<User> users = usersPage.getContent();

        List<UserDto> userDtos = users.stream()
                .map(u -> new UserDto(
                        u.getId(),
                        u.getUsername(),
                        u.getRole(),
                        u.isActive()))
                .collect(Collectors.toList());

        return new PageImpl<>(userDtos, pageable, usersPage.getTotalElements());
    }

    @Override
    public void update(UserDto userDto) {
        User user = userRepository.findById(userDto.getId()).orElseThrow(IllegalArgumentException::new);
        user.setRole(userDto.getRole());
        user.setActive(userDto.isActive());
        userRepository.save(user);
    }

    @Override
    public void disable(Integer id) {
        User user = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        if (user.isActive()) {
            user.setActive(false);
            userRepository.save(user);
        }
    }
}
