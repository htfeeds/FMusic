package com.htf.fmusic.controllers;

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
public class AdminController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

	@RequestMapping(value = { "/admin", "/admin/index" }, method = RequestMethod.GET)
	public String admin(Model model) {
		LOGGER.info("Welcome to Admin page!");
		return "admin/index";
	}
}
