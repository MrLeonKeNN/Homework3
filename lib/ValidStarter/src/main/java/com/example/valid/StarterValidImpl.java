package com.example.valid;

import com.example.interfaces.StarterValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StarterValidImpl implements ConstraintValidator<StarterValidation, String> {

    private boolean validateAccountNumber;
    private boolean validateCardNumber;
    private boolean validateName;
    private boolean validateUrl;


    @Override
    public void initialize(StarterValidation constraintAnnotation) {
        this.validateAccountNumber = constraintAnnotation.accountNumber();
        this.validateCardNumber = constraintAnnotation.cardNumber();
        this.validateName = constraintAnnotation.name();
        this.validateUrl = constraintAnnotation.url();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Если значение null – пусть @NotNull обработает это отдельно
        if (value == null) {
            return true;
        }
        boolean isValid = true;

        if (validateAccountNumber) {
            isValid = isValid && isValidateAccountNumber(value);
        }
        if (validateCardNumber) {
            isValid = isValid && validateCardNumber(value);
        }
        if (validateName) {
            isValid = isValid && validateName(value);
        }
        if (validateUrl) {
            isValid = isValid && validateUrl(value);
        }
        return isValid;
    }

    private boolean isValidateAccountNumber(String value) {
        return value.matches("\\d{20}");
    }

    private boolean validateCardNumber(String value) {
        return value.matches("\\d{16}");
    }

    private boolean validateName(String value) {
        return value.matches("[A-Za-zА-Яа-я\\s]+");
    }

    private boolean validateUrl(String value) {
        return value.matches("^(https?|ftp)://[^\\s/$.?#].[^\\s]*$");
    }
}
