package ie.todolist.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ie.todolist.models.Users;

public class UsersValidation implements Validator {

	@Override
	public boolean supports(Class<?> cls) {
		return Users.class.isAssignableFrom(cls);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "user", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "password", "field.required");
	}
	
}
