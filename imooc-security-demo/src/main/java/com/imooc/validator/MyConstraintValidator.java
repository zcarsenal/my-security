package com.imooc.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyConstraintValidator implements ConstraintValidator<MyConstraint, String> {
  @Override
  public void initialize(MyConstraint constraintAnnotation) {
    System.out.println("MyConstraint init");
  }

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    System.out.println(s);
    return true;
  }
}
