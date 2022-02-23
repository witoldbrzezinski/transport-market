package pl.witoldbrzezinski.transportmarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.witoldbrzezinski.transportmarket.entity.Load;
import pl.witoldbrzezinski.transportmarket.service.LoadService;

@Controller
public class LoadController {
	
	@Autowired
	LoadService service;
	
	@RequestMapping("/")
	public String index(Model model) {
		
		List<Load> loadList = service.getAllLoads();
		
		model.addAttribute("loads",loadList);
		
		return "index.html";
	}
	

}
