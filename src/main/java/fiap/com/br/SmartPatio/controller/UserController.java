package fiap.com.br.SmartPatio.controller;

import fiap.com.br.SmartPatio.domainmodel.User;
import fiap.com.br.SmartPatio.domainmodel.enums.UserRole;
import fiap.com.br.SmartPatio.service.FilialServiceImpl;
import fiap.com.br.SmartPatio.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final FilialServiceImpl filialService;
    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(FilialServiceImpl filialService,
                          UserServiceImpl userService,
                          PasswordEncoder passwordEncoder) {
        this.filialService = filialService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("filiais", filialService.findAll());
        model.addAttribute("roles", UserRole.values());
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@Valid @ModelAttribute("user") User user,
                                  BindingResult result,
                                  Model model) {

        if (result.hasErrors()) {
            model.addAttribute("filiais", filialService.findAll());
            model.addAttribute("roles", UserRole.values());
            return "register";
        }

        Long filialId = user.getFilial().getId();
        user.setFilial(filialService.findById(filialId));

        user.setSenha(passwordEncoder.encode(user.getSenha()));

        userService.save(user);
        return "redirect:/login?cadastroOk";
    }
}
