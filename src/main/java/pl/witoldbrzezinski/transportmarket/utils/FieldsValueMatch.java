package pl.witoldbrzezinski.transportmarket.utils;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = FieldsValueMatchValidator.class)
@Retention(RUNTIME)
@Target(TYPE)
public @interface FieldsValueMatch {

  String message() default "Fields values don't match!";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
