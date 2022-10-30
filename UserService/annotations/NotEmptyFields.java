package acs.annotations;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = NotEmptyFieldsValidator.class)
public @interface NotEmptyFields {

    String message() default "List cannot contain empty fields";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}