package com.globalsprint.globalsprint1.utils;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = DateValidator.class)
@Documented
public @interface IsAfter{
   String message() default "Date of birth is invalid";
   String current();
   Class<?>[] groups() default {};
   Class<? extends Payload>[] payload() default {};
}