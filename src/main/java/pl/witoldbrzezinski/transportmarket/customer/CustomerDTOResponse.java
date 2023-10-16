package pl.witoldbrzezinski.transportmarket.customer;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CustomerDTOResponse {

  private Long id;
  private String name;
  private String country;
  private String city;
  private String email;
  private String phone;
}
