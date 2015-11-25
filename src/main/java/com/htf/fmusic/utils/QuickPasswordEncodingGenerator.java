package com.htf.fmusic.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author HTFeeds
 */
public class QuickPasswordEncodingGenerator {
	
	public static void main(String[] args) {
		String password = "admin";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println(passwordEncoder.encode(password));
	}

}