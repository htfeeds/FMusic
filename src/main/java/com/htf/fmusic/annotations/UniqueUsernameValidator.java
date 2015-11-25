package com.htf.fmusic.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.htf.fmusic.services.UserService;

/**
 * @author HTFeeds
 */
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

	private final UserService userService;

	@Autowired
	public UniqueUsernameValidator(UserService userService) {
		super();
		this.userService = userService;
	}

	@Override
	public void initialize(UniqueUsername arg0) {

	}

	@Override
	public boolean isValid(String username, ConstraintValidatorContext arg1) {
		return userService.isUsernameUnique(null, username);
	}

}
