package com.htf.fmusic.admin_controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author HTFeeds
 */
@Controller
@RequestMapping("/admin")
public class DashboardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardController.class);

    @RequestMapping(value = { "", "/dashboard" }, method = RequestMethod.GET)
    public String admin(Model model) {
        LOGGER.info("Welcome to Admin page!");
        return "admin/dashboard/index";
    }
}
