package pl.witoldbrzezinski.transportmarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
		
		return "add-load.html";
	}
	
	@PostMapping("confirmLoadAdded")
	public String saveLoad(@ModelAttribute("load") Load load) {
		service.saveOrUpdateLoad(load);
		return "redirect:/";
	}
	
	@RequestMapping("/updateLoad/{loadId}")
	public String updateLoad(@PathVariable("loadId") long loadId, Model model) {
		
		Load load = service.getLoad(loadId);
		model.addAttribute("load",load);
		
		return "update-load.html";
	}
	
	@PostMapping("updateLoadConfirm/{loadId}")
	public String updateLoadConfrim(@PathVariable("loadId") long loadId, @ModelAttribute("load") Load load) {
		service.saveOrUpdateLoad(load);
		return "redirect:/";
	}
	
	@RequestMapping("deleteLoad/{loadId}")
	public String deleteLoad(@PathVariable("loadId") long loadId, @ModelAttribute("load") Load load) {
		service.deleteLoad(load);
		return "redirect:/";
	}

}
