package pl.witoldbrzezinski.transportmarket.security;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

  private final UserServiceImpl userService;

  @GetMapping("/login")
  public String getLoginPage() {
    return "login.html";
  }

  @GetMapping("/register")
  public String getRegisterPage(Model model) {
    model.addAttribute("user", new UserEntity());
    return "register.html";
  }

  @PostMapping("/register")
  public String register(
      @ModelAttribute("user") @Valid UserDTORegisterRequest user, BindingResult result, Model model)
      throws Exception {
    if (userService.checkIfEmailExist(user.getEmail())
        && userService.checkIfUsernameExist(user.getUsername())) {
      return "registration-existing-user.html";
    }
    try {
      UserDTORegisterResponse registeredUser = userService.registerNewUser(user);
      if (registeredUser != null
          && !user.getUsername().isBlank()
          && !user.getPassword().isBlank()
          && !user.getMatchingPassword().isBlank()
          && !user.getEmail().isBlank()) {
        return "redirect:/";
      }
    } catch (Exception exc) {
      return "registration-error.html";
    }
    return "register.html";
  }

  @GetMapping("/myProfile")
  public String getMyProfilePage(Model model) {
    UserEntity user = userService.getCurrentUser();
    model.addAttribute("user", user);
    return "profile.html";
  }
}
