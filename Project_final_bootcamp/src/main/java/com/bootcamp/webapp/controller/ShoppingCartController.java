package com.bootcamp.webapp.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bootcamp.webapp.model.*;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ShoppingCartController {
	UserService userService = null;
	UserServiceFactory userServiceFactoy = null;
	ShoppingCartService shoppingService = null;
	ShoppingServiceFactory shoppingFactory = null;

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView mvc = new ModelAndView("home");

		return mvc;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {

		ModelAndView mvc = new ModelAndView("register");
		return mvc;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam("id_user") String id_user,
			@RequestParam("password") String password) {
		boolean res = false;
		ModelAndView mvc = null;
		userService = userServiceFactoy.getRemoteServiceUsingWebService();
		res = userService.loginUser(id_user, password);
		if (res) {

			mvc = new ModelAndView("loginOk");
			mvc.addObject("message", "Welcome - " + id_user
					+ " have a nice day!");
		} else {
			mvc = new ModelAndView("home");
			mvc.addObject("message",
					"User or Password invalidate, please insert a correct one");
		}

		return mvc;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(@RequestParam("id_user") String id_user,

	@RequestParam("password") String pass,

	@RequestParam("email") String email,

	@RequestParam("phone") String phone) {
		String query;
		boolean res = false;

		ArrayList<String> a;
		ModelAndView mvc = null;
		a = new ArrayList<String>();
		a.add(id_user);
		a.add(pass);
		a.add(email);
		a.add(phone);
		res = ifEmpty(a);
		if (res) {
			mvc = new ModelAndView("register");
			mvc.addObject("message", "Please insert the fields correctly");
			return mvc;
		} else {
			WebUser webUser = new WebUser();
			webUser.setId_user(id_user);
			webUser.setPassword(pass);
			webUser.setEmail(email);

			Phone telephone = new Phone();
			telephone.setNumber(Integer.parseInt(phone));

			userService = userServiceFactoy.getRemoteServiceUsingWebService();
			userService.createUser(webUser, telephone);

			mvc = new ModelAndView("home");
			mvc.addObject("message", "The registration is Ok - Welcome "
					+ id_user + ", you can login now :) ");
			return mvc;
		}
	}

	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public ModelAndView findProductByName(
			@RequestParam("nameProduct") String nameProduct) {
		ArrayList<Product> list;

		shoppingService = shoppingFactory.getRemoteServiceUsingWebService();
		list = shoppingService.findProductByName(nameProduct);
		ModelAndView mvc = new ModelAndView("loginOk");
		mvc.addObject("list", list);

		return mvc;
	}

	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public ModelAndView findProductByCategory(

	@RequestParam("categoryProduct") String categoryProduct) {

		ArrayList<Product> listP;

		shoppingService = shoppingFactory.getRemoteServiceUsingWebService();
		listP = shoppingService.findProductByName(categoryProduct);
		ModelAndView mvc = new ModelAndView("loginOk");
		mvc.addObject("list", listP);

		return mvc;
	}

	@RequestMapping(value = "/saveShoppingCart", method = RequestMethod.POST)
	public ModelAndView saveShoppingCart(
			@RequestParam("objectUser") WebUser user,
			@RequestParam("shoppingCart") ShoppingCart shoppingcart) {
		// the webuser has the id_shopping_cart then we need to save all that
		shoppingService = shoppingFactory.getRemoteServiceUsingWebService();
		shoppingService.saveCart(user, shoppingcart);
		ModelAndView mvc = new ModelAndView("saveShoppingCart");
		mvc.addObject("message", "The cart was save correctry");

		return mvc;
	}

	// The webuser create a account and the account come to this method and
	// create the order with the line item
	@RequestMapping(value = "/buyProduct", method = RequestMethod.POST)
	public ModelAndView buyProduct(
			@RequestParam("accountUSer") Account account,
			@RequestParam("lineItem") ArrayList<LineItem> arrayLineItem) {

		shoppingService = shoppingFactory.getRemoteServiceUsingWebService();
		shoppingService.buyProduct(account, arrayLineItem);

		ModelAndView mvc = new ModelAndView("buyProduct");
		mvc.addObject("message", "Thank you for buy in Best Shopping!");
		return mvc;
	}

	// This method should place in other clase
	private boolean ifEmpty(ArrayList<String> array) {
		boolean res = false;
		ArrayList<String> a = array;
		for (int i = 0; i < a.size(); i++) {

			if (a.get(i).compareTo("") == 0) {
				res = true;
			}

		}

		return res;
	}
}