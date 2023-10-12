package pl.witoldbrzezinski.transportmarket.load;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoadController {
	
	@Autowired
	LoadService loadService;
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/")
	public String index(Model model,  @RequestParam("page") Optional<Integer> page, 
		      @RequestParam("size") Optional<Integer> size) {
		
		int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<LoadEntity> loadPage = loadService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("loadPage", loadPage);
        int totalPages = loadPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
		List<LoadEntity> loadList = loadService.getAllLoads();
		model.addAttribute("loads",loadList);
		
		return "index.html";
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/addNewLoad")
	public String addLoad(Model model) {
		
		LoadEntity load = new LoadEntity();
		model.addAttribute("load",load);
		
		return "add-load.html";
	}
	
	@PostMapping("/confirmLoadAdded")
	public String saveLoad(@Valid @ModelAttribute("load") LoadEntity load, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-load.html";
		}
		loadService.saveOrUpdateLoad(load);
		return "redirect:/";
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@RequestMapping("/updateLoad/{loadId}")
	public String updateLoad(@PathVariable("loadId") long loadId, Model model) {
		LoadEntity load = loadService.getLoad(loadId);
		model.addAttribute("load",load);
		
		return "update-load.html";
	}
	
	@PostMapping("/updateLoadConfirm/{loadId}")
	public String updateLoadConfrim(@PathVariable("loadId") long loadId, @Valid @ModelAttribute("load") LoadEntity load, BindingResult result) {
		if (result.hasErrors()) {
			return "update-load.html";
		}
		loadService.saveOrUpdateLoad(load);
		return "redirect:/";
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping("/deleteLoad/{loadId}")
	public String deleteLoad(@PathVariable("loadId") long loadId, @ModelAttribute("load") LoadEntity load) {
		loadService.deleteLoad(load);
		return "redirect:/";
	}

}
