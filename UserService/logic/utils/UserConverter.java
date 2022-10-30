package acs.logic.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;
import acs.boundary.UserBoundaryWithPassword;
import org.springframework.stereotype.Component;
import acs.boundary.UserBoundary;
import acs.data.UserEntity;
import acs.utils.UserFullName;

@Component
public class UserConverter {

	public UserBoundary fromEntity(UserEntity entity) {
		UserBoundary rv = new UserBoundary();
		UserFullName name = new UserFullName(entity.getFirstName(), entity.getLastName());
		rv.setName(name);		
		rv.setBirthdate(new SimpleDateFormat("dd-MM-yyyy").format(entity.getBirthdate()));
		rv.setEmail(entity.getEmail());
		rv.setRoles(entity.getRoles().toArray(new String[0]));

		return rv;
	}

	public UserEntity toEntity(UserBoundaryWithPassword boundary) {
		UserEntity rv = new UserEntity();
		rv.setFirstName(boundary.getName().getFirst());
		rv.setLastName(boundary.getName().getLast());
		try {
			rv.setBirthdate(new SimpleDateFormat("dd-MM-yyyy").parse(boundary.getBirthdate()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		rv.setEmail(boundary.getEmail());
		rv.setPassword(boundary.getPassword());
		rv.setRoles(new HashSet<>(Arrays.asList(boundary.getRoles())));
		return rv;
	}
}
