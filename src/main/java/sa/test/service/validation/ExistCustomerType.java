package sa.test.service.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


@Documented
@Constraint(validatedBy = ExistCustomerTypeValidator.class)
@Target({ElementType.FIELD,ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistCustomerType {

	String message() default "This customer type does not exist";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
