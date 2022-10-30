package acs.rest;

import acs.boundary.UserBoundaryWithPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import acs.boundary.UserBoundary;
import acs.logic.UserService;
import acs.utils.SortBy;
import acs.utils.SortOrder;

@RestController
public class UserController {
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	/*--------------------- GET APIS ------------------- */

	// Get user
	@RequestMapping(path = "/users/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary getUser(@PathVariable("email") String email) {
		return userService.getUser(email);
	}

	// login
	@RequestMapping(path = "/users/login/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary login(@PathVariable("email") String email,
			@RequestParam(name = "password", required = false) String password) {
		return userService.login(email, password);
	}

	/*
	 * // Get all users with pagination
	 * 
	 * @RequestMapping(path = "/users/search", method = RequestMethod.GET, produces
	 * = MediaType.APPLICATION_JSON_VALUE) public UserBoundary[]
	 * getAllUsers(@RequestParam(name = "size", required = false, defaultValue =
	 * "10") int size,
	 * 
	 * @RequestParam(name = "page", required = false, defaultValue = "0") int page,
	 * 
	 * @RequestParam(name = "sortBy", required = false, defaultValue = "email")
	 * String sortBy,
	 * 
	 * @RequestParam(name = "sortOrder", required = false, defaultValue = "ASC")
	 * String sortOrder) { return userService.getAllUsers(size, page, sortBy,
	 * sortOrder).toArray(new UserBoundary[0]); }
	 */

	// Get all users by criteria type with pagination
	@RequestMapping(path = "/users/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary[] getAllUsers(
			@RequestParam(name = "criteriaType", required = false) String criteriaType,
			@RequestParam(name = "criteriaValue", required = false) String criteriaValue,
			@RequestParam(name = "size", required = false, defaultValue = "10") int size,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "sortBy", required = false, defaultValue = "email") String sortBy,
			@RequestParam(name = "sortOrder", required = false, defaultValue = "ASC") String sortOrder) {
		return userService.getAllUsers(criteriaType, criteriaValue, size, page, sortBy, sortOrder).toArray(new UserBoundary[0]);
	}

	/*--------------------- POST APIS ------------------- */

	// Store user
	@RequestMapping(path = "/users", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary createNewUser(@RequestBody UserBoundaryWithPassword input) {
		return userService.createUser(input);
	}

	/*--------------------- PUT APIS ------------------- */

	// Update user details
	@RequestMapping(path = "/users/{email}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateUser(@PathVariable("email") String email, @RequestBody UserBoundaryWithPassword update) {
		userService.updateUser(email, update);
	}

}
