package com.htf.fmusic.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author HTFeeds
 */
@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = { PhoneNumberValidator.class })
public @interface PhoneNumber {
    String message() default "Please enter a valid number.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
