package com.ca.security.roles.demo78.services.Impl;

import com.ca.security.roles.demo78.services.SignupService;
import com.ca.security.roles.demo78.services.UserService;
import com.ca.security.roles.demo78.transfer.UserRegDto;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SignupServiceImpl implements SignupService {

    private static final Logger logger = LoggerFactory.getLogger(SignupServiceImpl.class);

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void signup(UserRegDto userRegDto) {
        userService.create(userRegDto);
        logger.info("New user is saved: {}", userRegDto.getUsername());
    }
}
