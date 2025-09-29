package fiap.com.br.SmartPatio.controller;

import fiap.com.br.SmartPatio.domainmodel.Carrapato;
import fiap.com.br.SmartPatio.domainmodel.HistoricMotorcycleFilial;
import fiap.com.br.SmartPatio.domainmodel.User;
import fiap.com.br.SmartPatio.domainmodel.enums.HistoricMotorcycleStatus;
import fiap.com.br.SmartPatio.service.CarrapatoServiceImpl;
import fiap.com.br.SmartPatio.service.HistoricMotorcycleFilialServiceImpl;
import fiap.com.br.SmartPatio.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class DashboardController {

    private final HistoricMotorcycleFilialServiceImpl historicoService;
    private final CarrapatoServiceImpl carrapatoService;
    private final UserServiceImpl userService;

    public DashboardController(HistoricMotorcycleFilialServiceImpl historicoService,
                               CarrapatoServiceImpl carrapatoService, UserServiceImpl userService) {
        this.historicoService = historicoService;
        this.carrapatoService = carrapatoService;
        this.userService = userService;
    }


    @GetMapping("/")
    public String dashboard(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        List<HistoricMotorcycleFilial> ativos = historicoService.findByStatusAndFilial(HistoricMotorcycleStatus.ATIVA, user.getFilial());
        List<HistoricMotorcycleFilial> finalizadas = historicoService.findByStatusAndFilial(HistoricMotorcycleStatus.FINALIZADA, user.getFilial());
        List<Carrapato> carrapatos = carrapatoService.findTop5ByOrderByNivelBateriaAsc();

        List<String> carrapatoLabels = carrapatos.stream()
                .map(Carrapato::getCodigoSerial)
                .toList();

        List<Integer> bateriaNiveis = carrapatos.stream()
                .map(Carrapato::getNivelBateria)
                .toList();

        model.addAttribute("carrapatoLabels", carrapatoLabels);
        model.addAttribute("bateriaNiveis", bateriaNiveis);

        model.addAttribute("user", user);
        model.addAttribute("ativos", ativos);
        model.addAttribute("finalizadas", finalizadas);
        model.addAttribute("carrapatos", carrapatos);

        return "index";
    }
}
