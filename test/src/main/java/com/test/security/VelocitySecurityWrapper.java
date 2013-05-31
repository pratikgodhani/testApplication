package com.test.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class VelocitySecurityWrapper {
	
	
	public static boolean isAuthenticated(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.isAuthenticated();
	}
	
	public static boolean isAuthorizedUser(){
		return getUserAuthorities().contains("ROLE_USER");
	}
	
	public static boolean isAuthorizedAdmin(){
		return getUserAuthorities().contains("ROLE_ADMIN");
	}

	public static String getUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

	public static Set<String> getUserAuthorities() {	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Set<String> roles = new HashSet<String>();

		Collection<? extends GrantedAuthority> gratedAuthorities = authentication.getAuthorities();
		for (GrantedAuthority ga : gratedAuthorities) {
			roles.add(ga.getAuthority());
		}
		return roles;
	}
}