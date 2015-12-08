package com.htf.fmusic.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author HTFeeds
 */
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    @Override
    public void initialize(PhoneNumber arg0) {
    }

    @Override
    public boolean isValid(String number, ConstraintValidatorContext arg1) {
        try {
            if (number == null || number.isEmpty()) {
                return true;
            }

            Double.parseDouble(number);
            if (number.length() > 9 && number.length() < 13) {
                return true;
            }
        } catch (NumberFormatException e) {
        }
        return false;
    }

}
