package com.htf.fmusic.user_controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.htf.fmusic.models.Playlist;
import com.htf.fmusic.models.User;
import com.htf.fmusic.services.PlaylistService;
import com.htf.fmusic.services.UserService;

/**
 * @author HTFeeds
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final PlaylistService playlistService;
    private final UserService userService;

    @Autowired
    public UserController(PlaylistService playlistService, UserService userService) {
        super();
        LOGGER.info("Inside UserController constructor.");
        this.playlistService = playlistService;
        this.userService = userService;
    }

    @RequestMapping(value = { "/{username}" }, method = RequestMethod.GET)
    public String index(@PathVariable String username, Model model) {
        User user = userService.findByUsername(username);
        if (user != null) {
            //Throws Exception
        }

        List<Playlist> userPlaylists = playlistService.getAllUserPlaylists(username);

        model.addAttribute("user", user);
        model.addAttribute("userPlaylists", userPlaylists);

        return "user/index";
    }

}
