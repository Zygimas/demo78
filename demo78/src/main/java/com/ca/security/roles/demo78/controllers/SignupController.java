package com.ca.security.roles.demo78.controllers;

import com.ca.security.roles.demo78.services.SignupService;
import com.ca.security.roles.demo78.transfer.UserRegDto;
import com.ca.security.roles.demo78.utils.SignupValidator;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {

    private static final Logger logger = LoggerFactory.getLogger(SignupController.class);

    private SignupService signupService;

    private SignupValidator signupValidator;

    @Autowired
    public void setSignupService(SignupService signupService) {
        this.signupService = signupService;
    }

    @Autowired
    public void setSignupValidator(SignupValidator signupValidator) {
        this.signupValidator = signupValidator;
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new UserRegDto());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("user") @Valid UserRegDto userRegDto, BindingResult result) {
        logger.info("Request to register a new user: {}", userRegDto.getUsername());

        signupValidator.validate(userRegDto, result);
        if (result.hasErrors()) {
            return "signup";
        }
        signupService.signup(userRegDto);
        return "redirect:/login";
    }
}
