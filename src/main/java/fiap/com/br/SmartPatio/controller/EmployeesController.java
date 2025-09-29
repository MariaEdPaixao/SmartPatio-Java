package fiap.com.br.SmartPatio.controller;

import fiap.com.br.SmartPatio.domainmodel.User;
import fiap.com.br.SmartPatio.service.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class EmployeesController {

    private final UserService userService;
    private final EmployeesService employeesService;

    public EmployeesController(UserService userService, EmployeesService employeesService) {
        this.userService = userService;
        this.employeesService = employeesService;
    }

    @GetMapping("/funcionarios")
    public String listFuncionarios(Model model, Principal principal) {
        User gestor = userService.findByEmailOrThrow(principal.getName());
        Long filialId = gestor.getFilial().getId();

        var funcionarios = employeesService.listarFuncionariosDaFilial(filialId, gestor.getId());

        model.addAttribute("funcionarios", funcionarios);
        model.addAttribute("gestor", gestor);

        return "funcionarios/list";
    }
}
