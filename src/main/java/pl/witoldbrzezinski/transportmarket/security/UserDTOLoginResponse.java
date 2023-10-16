package pl.witoldbrzezinski.transportmarket.security;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserDTOLoginResponse {

  private static final String TYPE = "Bearer";

  private String token;
  private String username;
  private List<String> roles;
}
