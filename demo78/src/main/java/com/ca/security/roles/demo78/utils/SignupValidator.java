package com.ca.security.roles.demo78.utils;

import com.ca.security.roles.demo78.services.UserService;
import com.ca.security.roles.demo78.transfer.UserRegDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SignupValidator implements Validator {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return UserRegDto.class.equals(clazz);
    }

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        UserRegDto userRegDto = (UserRegDto) target;

        if (userService.findByUsername(userRegDto.getUsername()).isPresent()) {
            errors.rejectValue("username", "", String.format("User %s already exists!", userRegDto.getUsername()));
        }

        if (!userRegDto.getPassword().equals(userRegDto.getMatchingPassword())) {
            errors.rejectValue("password", "", "Password not matching");
        }
    }
}
