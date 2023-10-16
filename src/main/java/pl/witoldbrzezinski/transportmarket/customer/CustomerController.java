package pl.witoldbrzezinski.transportmarket.customer;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService customerService;

  @Secured({"ROLE_ADMIN", "ROLE_USER"})
  @GetMapping()
  public String getAllCustomers(Model model) {

    List<CustomerDTOResponse> customers = customerService.getAll();
    model.addAttribute("customers", customers);
    return "customer.html";
  }

  // TODO implements CRUD methods

}
