package com.htf.fmusic.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.htf.fmusic.services.UserService;

/**
 * @author HTFeeds
 */
@RestController
@RequestMapping("/rest/available")
public class CheckAvailableApi {
    
    private final UserService userService;

    @Autowired
    public CheckAvailableApi(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    public String isUserAvailable(Integer id, @RequestParam String username) {
        Boolean available = userService.isUsernameUnique(id, username);
        if (available) {
            return available.toString();
        } else {
            return "Such username already exists.";
        }
    }

    @RequestMapping(value = "/email", method = RequestMethod.GET)
    public String isEmailAvailable(Integer id, @RequestParam String email) {
        Boolean available = userService.isEmailUnique(id, email);
        if (available) {
            return available.toString();
        } else {
            return "Such email already exists.";
        }
    }
}
