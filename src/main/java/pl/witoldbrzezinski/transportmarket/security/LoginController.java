package pl.witoldbrzezinski.transportmarket.security;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

  @Autowired UserService userService;

  @GetMapping("/login")
  public String getloginPage() {
    return "login.html";
  }

  @GetMapping("/register")
  public String getRegisterPage(Model model) {
    model.addAttribute("user", new UserEntity());
    return "register.html";
  }

  @PostMapping("/register")
  public String register(
      @ModelAttribute("user") @Valid UserEntity user, BindingResult result, Model model)
      throws Exception {
    if (userService.checkIfEmailExist(user.getEmail())
        && userService.checkIfUsernameExist(user.getUsername())) {
      return "registration-existing-user.html";
    }
    try {
      UserEntity registeredUser =
          userService.registerUser(
              user.getUsername(), user.getPassword(), user.getMatchingPassword(), user.getEmail());
      if (registeredUser != null
          && !user.getUsername().isBlank()
          && !user.getPassword().isBlank()
          && !user.getMatchingPassword().isBlank()
          && !user.getEmail().isBlank()) {
        return "redirect:/";
      }
    } catch (Exception exc) {
      exc.printStackTrace();
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
