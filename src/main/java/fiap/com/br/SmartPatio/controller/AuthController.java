package fiap.com.br.SmartPatio.controller;

import fiap.com.br.SmartPatio.controller.dto.ResetPassordDTO;
import fiap.com.br.SmartPatio.domainmodel.User;
import fiap.com.br.SmartPatio.domainmodel.enums.UserRole;
import fiap.com.br.SmartPatio.service.AuthService;
import fiap.com.br.SmartPatio.service.FilialService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final AuthService authService;
    private final FilialService filialService;

    public AuthController(AuthService authService, FilialService filialService) {
        this.authService = authService;
        this.filialService = filialService;
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error,
                        @RequestParam(required = false) String username,
                        Model model) {
        if (error != null) {
            model.addAttribute("error", true);
            model.addAttribute("username", username);
        }
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

        boolean sucesso = authService.registerUser(user);
        if (!sucesso) {
            model.addAttribute("emailDuplicado", true);
            model.addAttribute("filiais", filialService.findAll());
            model.addAttribute("roles", UserRole.values());
            return "register";
        }

        return "redirect:/login?cadastroOk";
    }

    @GetMapping("/redefinir-senha")
    public String showResetForm(Model model) {
        model.addAttribute("user", new ResetPassordDTO());
        return "redefinir-senha";
    }

    @PostMapping("/redefinir-senha")
    public String processReset(@Valid @ModelAttribute("user") ResetPassordDTO dto,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            return "redefinir-senha";
        }

        boolean sucesso = authService.resetPassword(dto);
        model.addAttribute(sucesso ? "senhaAtualizada" : "erro", true);

        return "redefinir-senha";
    }
}
