package com.bootcamp.webapp.model;

public class UserServiceProxy implements UserService {

	UserService implement;

	protected UserServiceProxy(UserService imp) {
		this.implement = imp;
	}

	@Override
	public void createUser(WebUser user, Phone p) {

		implement.createUser(user, p);

	}

	@Override
	public boolean loginUser(String id_user, String pass) {
		return implement.loginUser(id_user, pass);

	}

}
