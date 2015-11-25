package com.htf.fmusic.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.htf.fmusic.enums.State;
import com.htf.fmusic.models.Role;
import com.htf.fmusic.models.User;
import com.htf.fmusic.services.RoleService;
import com.htf.fmusic.services.UserService;
import com.htf.fmusic.utils.FmusicFunctions;

/**
 * @author HTFeeds
 */
@Controller
@RequestMapping("/admin/user")
@SessionAttributes("roles")
public class UserManagementController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserManagementController.class);

    private static final String AVATAR_LOCATION = "E:/Upload/";

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    UserManagementController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
    public String listUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin/user/list";
    }

    @RequestMapping(value = "/details-{id}", method = RequestMethod.GET)
    public String details(@PathVariable Integer id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "admin/user/details";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String newUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("states", initializeStates());
        model.addAttribute("roles", initializeRoles());
        return "admin/user/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult result, @RequestParam String save,
            @RequestParam MultipartFile image, Model model) throws IOException {
        if (!userService.isUsernameUnique(user.getId(), user.getUsername())) {
            FieldError usernameError = new FieldError("user", "username",
                    messageSource.getMessage("UniqueUsername.user.username", new String[] { user.getUsername() }, Locale.getDefault()));
            result.addError(usernameError);
        }
        if (!userService.isEmailUnique(user.getId(), user.getEmail())) {
            FieldError emailError = new FieldError("user", "email",
                    messageSource.getMessage("UniqueEmail.user.email", new String[] { user.getEmail() }, Locale.getDefault()));
            result.addError(emailError);
        }
        if (image.getSize() > 1024000) {
            LOGGER.info("\n\nThe file is too large\n");
        }
        if (result.hasErrors()) {
            return "admin/user/create";
        }
        if (image.getSize() > 0) {
            user.setImageUrl(FmusicFunctions.uploadImage(image, AVATAR_LOCATION));
        }
        LOGGER.info("Nothing wrong with this user {}", user);
        User u = userService.create(user);
        if (save.equals("Create and Edit")) {
            return "redirect:edit-" + u.getId();
        } else {
            return "redirect:list";
        }
    }

    @RequestMapping(value = "/edit-{id}", method = RequestMethod.GET)
    public String editUser(@PathVariable Integer id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("states", initializeStates());
        model.addAttribute("roles", initializeRoles());
        return "admin/user/edit";
    }

    @RequestMapping(value = "/edit-{id}", method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult result, @PathVariable Integer id, @RequestParam String save, Model model) {
        if (!userService.isUsernameUnique(user.getId(), user.getUsername())) {
            FieldError usernameError = new FieldError("user", "username",
                    messageSource.getMessage("UniqueUsername.user.username", new String[] { user.getUsername() }, Locale.getDefault()));
            result.addError(usernameError);
        }
        if (!userService.isEmailUnique(user.getId(), user.getEmail())) {
            FieldError emailError = new FieldError("user", "email",
                    messageSource.getMessage("UniqueEmail.user.email", new String[] { user.getEmail() }, Locale.getDefault()));
            result.addError(emailError);
        }
        if (result.hasErrors()) {
            return "admin/user/edit";
        }
        LOGGER.info("Nothing wrong with this user {}", user);
        User u = userService.update(user);
        if (save.equals("Save and Continue")) {
            return "redirect:edit-" + u.getId();
        } else {
            return "redirect:list";
        }
    }

    @RequestMapping(value = "/update-avatar-{id}", method = RequestMethod.POST)
    public String updateAvatar(@PathVariable Integer id, @RequestParam MultipartFile newImage, Model model) throws IOException {
        LOGGER.info("\n\nUpdating User Avatar\n");
        if (newImage.getSize() == 0) {
            LOGGER.info("\n\nMissing file\n");
        }
        if (newImage.getSize() > 1024000) {
            LOGGER.info("\n\nThe file is too large\n");
        }
        String newImageUrl = FmusicFunctions.uploadImage(newImage, AVATAR_LOCATION);
        User u = userService.updateAvatar(id, newImageUrl);
        return "redirect:edit-" + u.getId();
    }

    @RequestMapping(value = "/update-password-{id}", method = RequestMethod.POST)
    public String updatePassword(@PathVariable Integer id, @RequestParam String newPassword, Model model) {
        LOGGER.info("\n\nUpdating User Password\n");
        User u = userService.updatePassword(id, newPassword);
        return "redirect:edit-" + u.getId();
    }

    @RequestMapping(value = "/delete-{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable Integer id) {
        return "redirect:/admin/user/list";
    }

    private List<Role> initializeRoles() {
        return roleService.findAll();
    }

    private List<String> initializeStates() {
        LOGGER.info("Create states ModelAttribute");

        List<String> states = new ArrayList<String>();
        states.add(State.ACTIVE.getState());
        states.add(State.DELETED.getState());
        states.add(State.INACTIVE.getState());
        states.add(State.LOCKED.getState());
        return states;
    }

}
