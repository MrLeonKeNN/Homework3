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
        return false;
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
