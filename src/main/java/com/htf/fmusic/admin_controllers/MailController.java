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
@RequestMapping("/admin/mail")
public class MailController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailController.class);

    @RequestMapping(value = { "/", "/inbox" }, method = RequestMethod.GET)
    public String index(Model model) {
        LOGGER.info("Admin Mailbox");
        return "admin/mail/index";
    }
}
