package com.codegym.dto.customValidate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BirthDateValidator implements ConstraintValidator<BirthDay, String> {

    @Override
    public boolean isValid(final String valueToValidate, final ConstraintValidatorContext context) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate day = LocalDate.parse(valueToValidate, formatter);
        LocalDate currentDate = LocalDate.now();
        return ChronoUnit.YEARS.between(day, currentDate) >= 18;
    }
}