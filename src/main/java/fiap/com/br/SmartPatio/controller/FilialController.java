package fiap.com.br.SmartPatio.controller;

import fiap.com.br.SmartPatio.domainmodel.HistoricMotorcycleFilial;
import fiap.com.br.SmartPatio.domainmodel.User;
import fiap.com.br.SmartPatio.domainmodel.enums.HistoricMotorcycleStatus;
import fiap.com.br.SmartPatio.service.HistoricMotorcycleFilialServiceImpl;
import fiap.com.br.SmartPatio.service.MotorcycleEntryExitServiceImpl;
import fiap.com.br.SmartPatio.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/filial")
public class FilialController {


    private final MotorcycleEntryExitServiceImpl entryExitService;
    private final HistoricMotorcycleFilialServiceImpl historicoService;
    private final UserServiceImpl userService;

    public FilialController(MotorcycleEntryExitServiceImpl entryExitService, UserServiceImpl userService, HistoricMotorcycleFilialServiceImpl historicoService) {
        this.entryExitService = entryExitService;
        this.userService = userService;
        this.historicoService = historicoService;
    }

    @GetMapping
    public String filial(){
        return "filial/index";
    }

    @GetMapping("/entrada")
    public String entrada(Model model, Principal principal) {
        User usuario = searchLoggedInUser(principal);
        Long filialId = usuario.getFilial().getId();
        Long usuarioId = usuario.getId();

        try {
            HistoricMotorcycleFilial historico = entryExitService.registerEntry(filialId, usuarioId);
            model.addAttribute("placa", historico.getMoto().getPlaca());
            model.addAttribute("carrapato", historico.getCarrapato().getCodigoSerial());
            model.addAttribute("entradaRealizada", true);
        } catch (IllegalStateException e) {
            model.addAttribute("entradaRealizada", false);
        }

        return "filial/entrada";
    }


    @GetMapping("/saida")
    public String saida(Model model, Principal principal) {
        User user = searchLoggedInUser(principal);
        Long filialId = user.getFilial().getId();

        List<HistoricMotorcycleFilial> ativos = historicoService.findByStatusAndFilial(HistoricMotorcycleStatus.ATIVA, user.getFilial());
        model.addAttribute("ativos", ativos);

        if (!ativos.isEmpty()) {
            HistoricMotorcycleFilial historico = entryExitService.registerExitAleatoria(filialId, user.getId());
            model.addAttribute("placa", historico.getMoto().getPlaca());
            model.addAttribute("carrapato", historico.getCarrapato().getCodigoSerial());
        }

        return "filial/saida";
    }

    private User searchLoggedInUser(Principal principal) {
        String email = principal.getName();
        return userService.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Usuário não encontrado"));
    }
}
