package com.test.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class CustomAuthentication implements Authentication {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -134624898542713849L;
	String name;
    ArrayList<GrantedAuthority> authorities;
    Object credentials;
    Object details;
    Object principal;
    boolean authenticated;

	public CustomAuthentication(String name, ArrayList<GrantedAuthority> authorities,
			Object credentials, Object details, Object principal,
			boolean authenticated) {
		this.name = name;
		this.authorities = authorities;
		this.details = details;
		this.principal = principal;
		this.credentials = credentials;
		this.authenticated = authenticated;

	}

	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		
		return authorities;
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return principal;
	}

	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return details;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAuthenticated() {
		// TODO Auto-generated method stub
		return authenticated;
	}

	@Override
	public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
		this.authenticated = authenticated;

	}

}
