package ie.todolist.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ie.todolist.models.Activities;

public class ActivitiesValidation implements Validator {

	@Override
	public boolean supports(Class<?> cls) {
		return Activities.class.isAssignableFrom(cls);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "date", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "tasks", "field.required");
		
		
		/*
		 * Activities activities = (Activities) target;
		 * if(activities.getDate() != "data"){
			errors.rejectValue("date", "field.task");
		}*/		
	}
	
}
