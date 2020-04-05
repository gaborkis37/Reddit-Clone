package com.homeProj.domain.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = PasswordMatchValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordsMatch {

	String message() default "Password and Password confirmation do not match.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default{};
	
}
