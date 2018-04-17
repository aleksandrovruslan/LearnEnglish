package ru.aleksandrov.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class ValidatorUtil<T> {
    private static ValidatorFactory factory = Validation
            .buildDefaultValidatorFactory();
    private Validator validator;
    private Set<ConstraintViolation<T>> constraintViolations;

    public ValidatorUtil() {
        validator = factory.getValidator();
    }

    public boolean verifyEntity(T entity) {
        constraintViolations = validator.validate(entity);
        return constraintViolations.size() == 0;
    }

    public Set<ConstraintViolation<T>> getErrors() {
        return constraintViolations;
    }
}
