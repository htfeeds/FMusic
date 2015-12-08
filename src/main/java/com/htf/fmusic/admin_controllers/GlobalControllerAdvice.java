package com.htf.fmusic.admin_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.htf.fmusic.models.User;
import com.htf.fmusic.services.UserService;

/**
 * @author HTFeeds
 */
@ControllerAdvice
public class GlobalControllerAdvice {

    private final UserService userService;

    @Autowired
    GlobalControllerAdvice(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("loginModel")
    public User loginModel() {
        return getPrincipal();
    }

    public User getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            User user = userService.findByUsername(username);
            return user;
        } else {
            return null;
        }
    }
}
