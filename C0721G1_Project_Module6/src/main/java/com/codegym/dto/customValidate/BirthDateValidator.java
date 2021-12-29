package com.codegym.dto.customValidate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BirthDateValidator implements ConstraintValidator<BirthDay, String> {

    @Override
    public boolean isValid(final String valueToValidate, final ConstraintValidatorContext context) {
        try {
            if (valueToValidate != null) {
                int day = Integer.parseInt(valueToValidate.substring(0, 4));
                int currentDate = LocalDate.now().getYear();
                return currentDate - day >= 18 && currentDate - day < 60;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}