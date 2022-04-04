package pl.witoldbrzezinski.transportmarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pl.witoldbrzezinski.transportmarket.entity.Customer;
import pl.witoldbrzezinski.transportmarket.service.CustomerService;

@Controller
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/customers")
	public String getAllCustomers(Model model) {
		
		List<Customer> customerList = customerService.getAllCustomers();
		
		model.addAttribute("customers",customerList);
		
		return "customer.html";
	}

}
