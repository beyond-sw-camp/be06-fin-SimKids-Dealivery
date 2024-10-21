package org.example.orders.global.validator.annotation;

import jakarta.validation.Constraint;
import org.example.orders.global.validator.MinuteValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MinuteValidator.class)
public @interface MinuteCheck {
	String message() default "시작/종료 시간은 정각(00분) 또는 30분이어야 합니다.";

	Class[] groups() default {};

	Class[] payload() default {};
}
