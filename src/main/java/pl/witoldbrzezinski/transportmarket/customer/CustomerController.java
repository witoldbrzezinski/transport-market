package pl.witoldbrzezinski.transportmarket.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/customers")
	public String getAllCustomers(Model model) {
		
		List<CustomerEntity> customerList = customerService.getAllCustomers();
		
		model.addAttribute("customers",customerList);
		
		return "customer.html";
	}

}
