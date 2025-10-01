package fiap.com.br.SmartPatio.controller;

import fiap.com.br.SmartPatio.controller.dto.UpdateUserDTO;
import fiap.com.br.SmartPatio.domainmodel.User;
import fiap.com.br.SmartPatio.domainmodel.enums.UserRole;
import fiap.com.br.SmartPatio.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/perfil")
    public String profile(Model model, Principal principal) {
        User user = profileService.getUsuarioLogado(principal.getName());
        model.addAttribute("user", user);

        if (user.getRole().equals(UserRole.GESTOR)) {
            Long filialId = user.getFilial().getId();
            model.addAttribute("qtdFuncionarios", profileService.countBranchEmployees(filialId));
            model.addAttribute("qtdMotosAtivas", profileService.countActiveMotorcycles(user));
        }

        return "profile/index";
    }

    @GetMapping("/perfil/editar")
    public String showEditProfile(Model model, Principal principal) {
        User user = profileService.getUsuarioLogado(principal.getName());
        model.addAttribute("user", user);
        return "profile/edit";
    }

    @PostMapping("/perfil/editar")
    public String updateProfile(@Valid @ModelAttribute("user") UpdateUserDTO dto,
                                BindingResult result,
                                Principal principal) {
        if (result.hasErrors()) {
            return "profile/edit";
        }

        User user = profileService.getUsuarioLogado(principal.getName());
        profileService.updateProfile(user, dto);

        return "redirect:/perfil?editSuccess";
    }

    @PostMapping("perfil/deletar")
    public String deleteUser(Principal principal) {
        User user = profileService.getUsuarioLogado(principal.getName());
        profileService.deleteUser(user);

        return "redirect:/login?deletedSuccess";
    }
}
