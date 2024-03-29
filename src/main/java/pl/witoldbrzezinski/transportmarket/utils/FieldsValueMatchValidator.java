package pl.witoldbrzezinski.transportmarket.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.witoldbrzezinski.transportmarket.security.UserEntity;

@NoArgsConstructor
public class FieldsValueMatchValidator implements ConstraintValidator<FieldsValueMatch, Object> {

  @Autowired PasswordEncoder passwordEncoder;

  public void initialize(FieldsValueMatch constraintAnnotation) {
    passwordEncoder = new BCryptPasswordEncoder();
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {
    UserEntity user = (UserEntity) value;
    return passwordEncoder.matches(user.getMatchingPassword(), user.getPassword());
  }
}
