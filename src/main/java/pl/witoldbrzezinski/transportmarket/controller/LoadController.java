package pl.witoldbrzezinski.transportmarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pl.witoldbrzezinski.transportmarket.entity.Load;
import pl.witoldbrzezinski.transportmarket.service.LoadService;

@Controller
public class LoadController {
	
	@Autowired
	LoadService service;
	
	@GetMapping("/")
	public String index(Model model) {
		
		List<Load> loadList = service.getAllLoads();
		
		model.addAttribute("loads",loadList);
		
		return "index.html";
	}
	
	@GetMapping("/addNewLoad")
	public String addLoad(Model model) {
		
		Load load = new Load();
		model.addAttribute("load",load);
		
		return "new-load.html";
	}
	
	@PostMapping("confirmLoadAdded")
	public String saveLoad(@ModelAttribute("load") Load load) {
		System.out.println(load);
		service.saveLoad(load);
		return "redirect:/";
	}


}
