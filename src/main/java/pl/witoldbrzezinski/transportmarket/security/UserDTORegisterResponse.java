package pl.witoldbrzezinski.transportmarket.security;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserDTORegisterResponse {

  @NotNull private String username;
  @Email @NotNull private String email;
  @NotNull private String password;
}
