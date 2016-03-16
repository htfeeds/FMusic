package com.htf.fmusic.rest_controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.htf.fmusic.models.User;
import com.htf.fmusic.models.Views;
import com.htf.fmusic.services.UserService;

/**
 * @author HTFeeds
 */
@RestController
@RequestMapping("/rest/user")
public class UserControllerApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserControllerApi.class);

    private final UserService userService;

    @Autowired
    public UserControllerApi(UserService userService) {
        LOGGER.info("Inside constructor of UserControllerApi");
        this.userService = userService;
    }

    @JsonView(Views.ExtendedPublic.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable("id") Integer id) {
        User user = userService.findById(id);
        return user;
    }

    @JsonView(Views.Summary.class)
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<User> findAll() {
        List<User> users = userService.findAll();
        return users;
    }

}
