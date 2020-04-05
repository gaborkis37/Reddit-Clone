package com.homeProj.domain.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.homeProj.domain.User;

public class PasswordMatchValidator implements ConstraintValidator<PasswordsMatch, User> {

	@Override
	public boolean isValid(User user, ConstraintValidatorContext context) {
		return user.getPassword().equals(user.getConfirmPassword());
	}

}
