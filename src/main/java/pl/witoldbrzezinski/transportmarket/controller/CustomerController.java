package pl.witoldbrzezinski.transportmarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.witoldbrzezinski.transportmarket.entity.Customer;
import pl.witoldbrzezinski.transportmarket.service.CustomerService;

@Controller
public class CustomerController {
	
	@Autowired
	CustomerService service;
	
	@RequestMapping("/")
	public String index(Model model) {
		
		List<Customer> customerList = service.getAllCustomers();
		
		model.addAttribute("customers",customerList);
		
		return "index.html";
	}
	

}
