package org.example.orders.global.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.orders.global.constants.BaseResponseStatus;
import org.example.orders.global.exception.InvalidCustomException;
import org.example.orders.global.validator.annotation.MinuteCheck;

import java.time.LocalDateTime;

public class MinuteValidator implements ConstraintValidator<MinuteCheck, LocalDateTime> {
	@Override
	public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
		if (!(value.getMinute() == 0 || value.getMinute() == 30)) {
			throw new InvalidCustomException(BaseResponseStatus.PRODUCT_BOARD_REGISTER_FAIL_INVALID_MINUTE);
		}
		return true;
	}
}
