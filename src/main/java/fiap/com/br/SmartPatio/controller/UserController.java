package fiap.com.br.SmartPatio.controller;

import fiap.com.br.SmartPatio.controller.dto.UpdateUserDTO;
import fiap.com.br.SmartPatio.domainmodel.HistoricMotorcycleFilial;
import fiap.com.br.SmartPatio.domainmodel.User;
import fiap.com.br.SmartPatio.domainmodel.enums.HistoricMotorcycleStatus;
import fiap.com.br.SmartPatio.domainmodel.enums.UserRole;
import fiap.com.br.SmartPatio.service.FilialServiceImpl;
import fiap.com.br.SmartPatio.service.HistoricMotorcycleFilialServiceImpl;
import fiap.com.br.SmartPatio.service.UserServiceImpl;
import jakarta.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    private final FilialServiceImpl filialService;
    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final HistoricMotorcycleFilialServiceImpl historicoService;

    public UserController(FilialServiceImpl filialService,
                          UserServiceImpl userService,
                          PasswordEncoder passwordEncoder,
                          HistoricMotorcycleFilialServiceImpl historicoService) {
        this.filialService = filialService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.historicoService = historicoService;
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

    @GetMapping("/perfil")
    public String profile(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        List<HistoricMotorcycleFilial> ativos = historicoService.findByStatusAndFilial(HistoricMotorcycleStatus.ATIVA, user.getFilial());

        model.addAttribute("user", user);

        if (user.getRole().equals(UserRole.GESTOR)) {
            Long filialId = user.getFilial().getId();
            model.addAttribute("qtdFuncionarios", userService.

                    countByFilial(filialId));
            model.addAttribute("qtdMotosAtivas", ativos.size());
        }

        return "profile/index";
    }

    @GetMapping("/perfil/editar")
    public String showEditProfile(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        model.addAttribute("user", user);
        return "profile/edit";
    }

    @PostMapping("/perfil/editar")
    public String updateProfile(@Valid @ModelAttribute("user") UpdateUserDTO updatedUser,
                                BindingResult result,
                                Principal principal,
                                Model model) {
        if (result.hasErrors()) {
            return "profile/edit";
        }

        User user = userService.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        user.setNome(updatedUser.getNome());
        user.setEmail(updatedUser.getEmail());

        if (updatedUser.getSenha() != null && !updatedUser.getSenha().isBlank()) {
            user.setSenha(passwordEncoder.encode(updatedUser.getSenha()));
        }

        userService.save(user);

        return "redirect:/perfil?editSuccess";
    }

    @PostMapping("perfil/deletar")
    public String deleteUser(Principal principal) {
        User user = userService.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        this.userService.deleteById(user.getId());

        return "redirect:/login?deletedSuccess";
    }

    @GetMapping("/funcionarios")
    public String listFuncionarios(Model model, Principal principal) {
        User gestor = userService.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Long filialId = gestor.getFilial().getId();

        var funcionarios = userService.findByFilialId(filialId)
                .stream()
                .filter(u -> !u.getId().equals(gestor.getId()))
                .toList();

        model.addAttribute("funcionarios", funcionarios);
        model.addAttribute("gestor", gestor);

        return "funcionarios/list"; 
    }


}
