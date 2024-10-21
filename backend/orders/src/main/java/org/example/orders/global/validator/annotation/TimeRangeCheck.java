package org.example.orders.global.validator.annotation;

import jakarta.validation.Constraint;
import org.example.orders.global.validator.TimeRangeValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TimeRangeValidator.class)
public @interface TimeRangeCheck {
	String message() default "시작 시간은 09:00 ~ 22:00 사이여야 합니다.";

	Class[] groups() default {};

	Class[] payload() default {};
}