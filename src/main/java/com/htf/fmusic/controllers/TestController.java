package com.htf.fmusic.controllers;

import java.io.IOException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.htf.fmusic.models.User;
import com.htf.fmusic.services.UserService;
import com.htf.fmusic.utils.FmusicFunctions;

/**
 * @author HTFeeds
 */
@Controller
@RequestMapping("/test")
public class TestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    private final UserService userService;

    @Autowired
    TestController(UserService userService) {
        this.userService = userService;
    }

    //-------------------Test Upload file--------------------------------------------------------
    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String getUpload(Model model) {
        return "test/file_upload";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String postUpload(MultipartFile file, Model model) throws IOException {
        if (file.getSize() == 0) {
            LOGGER.info("FILE IS NULL! FILE IS NULL! FILE IS NULL! FILE IS NULL!");
            return "test/file_upload";
        } else if (file.getSize() > 1024000) {
            LOGGER.info("FILE IS TO LARGE! FILE IS TO LARGE! FILE IS TO LARGE! FILE IS TO LARGE!");
            return "test/file_upload";
        } else {
            String path = FmusicFunctions.uploadImage(file, "E:/Upload/");
            LOGGER.info("UPLOAD SUCCESSFULLY! {}" + path);
            model.addAttribute("fileName", file.getOriginalFilename());
            return "test/upload_success";
        }
    }

    //-------------------Test User Repository---------------------------------------------------
    @RequestMapping(value = "/edit-{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "test/edit_user";
    }

    @RequestMapping(value = "/edit-{id}", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute User user, BindingResult result, @PathVariable Integer id, @RequestParam String save, Model model) {
        User u = userService.update(user);

        model.addAttribute("user", u);
        model.addAttribute("save", save);

        return "test/edit_success";
    }

}
