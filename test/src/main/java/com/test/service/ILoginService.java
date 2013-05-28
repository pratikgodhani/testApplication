package com.test.service;

public interface ILoginService {

	boolean isValidUser(final String userName, final String password) throws Exception;
}
