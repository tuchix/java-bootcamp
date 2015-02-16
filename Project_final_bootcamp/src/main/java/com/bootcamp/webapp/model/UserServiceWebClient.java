package com.bootcamp.webapp.model;

public class UserServiceWebClient implements UserService {
	UserService webService;

	public UserServiceWebClient() {

		webService = new UserServiceWeb();

	}

	@Override
	public void createUser(WebUser user, Phone p) {
		webService.createUser(user, p);

	}

	@Override
	public boolean loginUser(String id_user, String pass) {

		return webService.loginUser(id_user, pass);

	}

}
