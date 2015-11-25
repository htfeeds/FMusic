package com.htf.fmusic.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.htf.fmusic.models.User;
import com.htf.fmusic.services.UserService;

/**
 * @author HTFeeds
 */
public class UsernameAuditorAware implements AuditorAware<User> {

	private static final Logger LOGGER = LoggerFactory.getLogger(UsernameAuditorAware.class);

	@Autowired
	private UserService userService;

	@Override
	public User getCurrentAuditor() {
		LOGGER.debug("Getting the user of authenticated user.");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated()) {
			LOGGER.debug("Current user is anonymous. Returning null.");
			return null;
		}

		Object principal = authentication.getPrincipal();

		if (principal instanceof UserDetails) {
			String username = ((org.springframework.security.core.userdetails.User) authentication.getPrincipal()).getUsername();
			com.htf.fmusic.models.User user = userService.findByUsername(username);

			LOGGER.debug("Returning user: {}", user);
			return user;
		} else {
			return null;
		}
	}
	
}
