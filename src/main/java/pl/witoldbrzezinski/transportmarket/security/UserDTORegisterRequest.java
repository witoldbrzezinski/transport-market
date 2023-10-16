package pl.witoldbrzezinski.transportmarket.security;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.witoldbrzezinski.transportmarket.customer.CustomerEntity;

import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserDTORegisterRequest {

  @NotNull private String username;
  @Email @NotNull private String email;
  @NotNull private String password;
  @NotNull @Transient private String matchingPassword;
  private CustomerEntity customer;
}
