package com.ca.security.roles.demo78.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Authentication authentication, HttpServletRequest request, ModelMap model) {
       /* if (authentication != null) {
            return "redirect:/";
        }
        if (request.getParameterMap().containsKey("error")) {
            model.addAttribute("error", true);
        }*/
         return "login";
    }

    @PostMapping ("/login/check")
    public String login(Authentication authentication, HttpServletRequest request, RequestBody username) {
        if (authentication != null) {
            return "redirect:/";
        }
        if (request.getParameterMap().containsKey("error")) {
           // model.addAttribute("error", true);
        }
        return "login";
    }
}
