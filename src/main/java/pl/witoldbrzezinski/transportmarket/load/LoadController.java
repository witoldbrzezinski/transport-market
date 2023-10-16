package pl.witoldbrzezinski.transportmarket.load;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class LoadController {

  private final LoadService loadService;

  @Secured({"ROLE_ADMIN", "ROLE_USER"})
  @GetMapping("/")
  public String index(
      Model model,
      @RequestParam("page") Optional<Integer> page,
      @RequestParam("size") Optional<Integer> size) {

    int currentPage = page.orElse(1);
    int pageSize = size.orElse(10);
    Page<LoadDTOResponse> loadPage =
        loadService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
    model.addAttribute("loadPage", loadPage);
    int totalPages = loadPage.getTotalPages();
    if (totalPages > 0) {
      List<Integer> pageNumbers =
          IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
      model.addAttribute("pageNumbers", pageNumbers);
    }
    List<LoadDTOResponse> loadList = loadService.getAll();
    model.addAttribute("loads", loadList);

    return "index.html";
  }

  @Secured({"ROLE_ADMIN", "ROLE_USER"})
  @GetMapping("/addNewLoad")
  public String addLoad(Model model) {
    LoadEntity load = new LoadEntity();
    model.addAttribute("load", load);
    return "add-load.html";
  }

  @Secured({"ROLE_ADMIN", "ROLE_USER"})
  @PostMapping("/confirmLoadAdded")
  public String saveLoad(
      @Valid @ModelAttribute("load") LoadDTORequest load, BindingResult result, Model model) {
    if (result.hasErrors()) {
      return "add-load.html";
    }
    loadService.save(load);
    return "redirect:/";
  }

  @Secured({"ROLE_ADMIN"})
  @RequestMapping("/updateLoad/{id}")
  public String updateLoad(@PathVariable("id") long id, Model model) {
    LoadDTOResponse load = loadService.getById(id);
    model.addAttribute("load", load);
    return "update-load.html";
  }

  @Secured({"ROLE_ADMIN"})
  @PostMapping("/updateLoadConfirm/{id}")
  public String updateLoadConfirm(
      Model model,
      @PathVariable("id") long id,
      @Valid @ModelAttribute("load") LoadDTORequest load,
      BindingResult result) {
    if (result.hasErrors()) {
      return "update-load.html";
    }
    loadService.update(id, load);
    return "redirect:/";
  }

  @Secured({"ROLE_ADMIN"})
  @RequestMapping("/deleteLoad/{id}")
  public String deleteLoad(Model model, @PathVariable("id") long id) {
    loadService.delete(id);
    return "redirect:/";
  }
}
