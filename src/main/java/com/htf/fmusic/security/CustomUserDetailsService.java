package com.htf.fmusic.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htf.fmusic.models.Role;
import com.htf.fmusic.models.User;
import com.htf.fmusic.services.UserService;

/**
 * @author HTFeeds
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Autowired
	private UserService userService;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LOGGER.info("Getting a User by username: {}", username);
		User user = userService.findByUsername(username);
		if (user == null) {
			LOGGER.info("User not found!");
			throw new UsernameNotFoundException("Username not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getState().equals("Active"), true,
				true, true, getGrantedAuthorities(user));
	}

	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (Role role : user.getRoles()) {
			LOGGER.info("Role: {}", role);
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getType()));
		}
		LOGGER.info("Authorities: {}", authorities);
		return authorities;
	}
}
