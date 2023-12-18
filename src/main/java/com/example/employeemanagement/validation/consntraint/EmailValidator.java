package com.example.employeemanagement.validation.consntraint;

import com.example.employeemanagement.validation.annotations.ValidEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
@Override
public void initialize(ValidEmail constraintAnnotation) {
        }

@Override
public boolean isValid(String email, ConstraintValidatorContext context) {
        if(email.length()<10||email.length()>300)
            return false;
        if(!email.matches("^[A-Za-z0-9+_.-]+@(.+)$"))
            return false;
        return true;
        }
}
