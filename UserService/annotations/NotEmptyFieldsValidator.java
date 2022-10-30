package acs.annotations;

import java.util.Set;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotEmptyFieldsValidator implements ConstraintValidator<NotEmptyFields, Set<String>> {
	@Override
	public boolean isValid(Set<String> objects, ConstraintValidatorContext context) {
		// each element in list can't be null or blank (empty string or only including white spaces)
        return objects.stream().allMatch(object -> object != null && !object.trim().isEmpty());
	}	
}