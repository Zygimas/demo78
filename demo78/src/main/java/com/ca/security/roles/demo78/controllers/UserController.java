package com.ca.security.roles.demo78.controllers;

import com.ca.security.roles.demo78.persist.entities.Role;
import com.ca.security.roles.demo78.services.UserService;
import com.ca.security.roles.demo78.transfer.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class UserController {

    private final static int PAGE_SIZE = 10;
    private final static String SORT_FIELD = "username";

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String list(@PageableDefault(size = PAGE_SIZE, sort = SORT_FIELD) Pageable pageable,
                       Model model) {
        Sort sort = Sort.by(Sort.Direction.ASC, SORT_FIELD);
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<UserDto> page = userService.findAll(pageable);
        model.addAttribute("page", page);
        return "users";
    }

    @GetMapping("/users/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        UserDto userDto = userService.getById(id);
        model.addAttribute("user", userDto);
        model.addAttribute("roles", Role.values());
        return "operations/usersedit";
    }

    @PutMapping("/users/update")
    public String update(@ModelAttribute("user") UserDto userDto) {
        userService.update(userDto);
        return "redirect:/users";
    }

    @PatchMapping("/users/disable/{id}")
    public String disable(@PathVariable Integer id) {
        userService.disable(id);
        return "redirect:/users";
    }
}

