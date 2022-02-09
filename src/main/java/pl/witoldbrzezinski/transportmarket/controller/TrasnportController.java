package pl.witoldbrzezinski.transportmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TrasnportController {
	
	@RequestMapping("/")
	public String index() {
		return "view/index.html";
	}

}
