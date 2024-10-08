package com.husky.validation;

import com.husky.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StateValidation implements ConstraintValidator<State, String> {
    /**
     *
     * @param s
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        //提供校验规则
        if (s == null) {
            return false;
        }
        if (s.equals("已发布") || s.equals("草稿")) {
            return true;
        }
        return false;
    }
}
