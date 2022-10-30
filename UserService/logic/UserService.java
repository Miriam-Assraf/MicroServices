package acs.logic;

import java.util.List;

import acs.boundary.UserBoundary;
import acs.boundary.UserBoundaryWithPassword;

public interface UserService {

	UserBoundary createUser(UserBoundaryWithPassword userBoundary);

	UserBoundary getUser(String email);
	
	UserBoundary login(String email, String password);

	void updateUser(String email, UserBoundaryWithPassword update);

	List<UserBoundary> getAllUsers(String criteriaType, String criteriaValue, int size, int page,
			String sortBy, String sortOrder);

}
