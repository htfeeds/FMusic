package com.htf.fmusic.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author HTFeeds
 */
public class GetPincipal {
	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	public static void main(String[] args) {
		GetPincipal getPrincipal = new GetPincipal();
		System.out.println(getPrincipal.getPrincipal());
	}
}
