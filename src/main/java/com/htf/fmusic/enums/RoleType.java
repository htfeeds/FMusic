package com.htf.fmusic.enums;

/**
 * @author HTFeeds
 */
public enum RoleType {
	USER("USER"), VIPUSER("VIP"), ADMIN("ADMIN"), DBA("DBA");

	String roleType;

	private RoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getRoleType() {
		return roleType;
	}
}