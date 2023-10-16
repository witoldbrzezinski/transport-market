package pl.witoldbrzezinski.transportmarket.security;

import pl.witoldbrzezinski.transportmarket.customer.CustomerEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserDTORegisterResponse {

  @NotNull private String username;
  @Email @NotNull private String email;
  @NotNull private String password;
}
