package com.htf.fmusic.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.htf.fmusic.models.Role;
import com.htf.fmusic.services.RoleService;

/**
 * @author HTFeeds
 */
@Component
public class RoleConverter implements Converter<Object, Role> {
	@Autowired
	RoleService roleService;

	@Override
	public Role convert(Object element) {
		Integer id = Integer.parseInt((String) element);
		Role role = roleService.findById(id);
		return role;
	}
}
