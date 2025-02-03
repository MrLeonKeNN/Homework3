package com.example.interfaces;

import com.example.valid.StarterValidImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = {StarterValidImpl.class})
@Target({ElementType.METHOD, ElementType.FIELD  })
@Retention(RetentionPolicy.RUNTIME)
public @interface StarterValidation {

    String message() default "Некорректное значение формата";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean accountNumber() default false;

    boolean cardNumber() default false;

    boolean name() default false;

    boolean url() default false;
}
