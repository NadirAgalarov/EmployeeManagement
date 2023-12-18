package com.example.employeemanagement.validation.consntraint;

import com.example.employeemanagement.validation.annotations.ValidPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
    @Override
    public void initialize(ValidPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value.length()<8||value.length()>20)
            return false;
        if(!value.matches("^.*(?=..*[0-9])*$"))
            return false;
        if(!value.matches("^.*(?=.*[a-z]).*$"))
            return false;
        if(!value.matches("^.*(?=.*[A-Z]).*$"))
            return false;
        if(!value.matches("^.*(?=.*[@#$%^&+=]).*$"))
            return false;
        return true;
    }
}


