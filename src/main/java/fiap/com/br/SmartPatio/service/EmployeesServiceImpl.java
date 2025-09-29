package fiap.com.br.SmartPatio.service;

import fiap.com.br.SmartPatio.domainmodel.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeesServiceImpl implements EmployeesService{
    private final UserService userService;

    public EmployeesServiceImpl(UserService userService) {
        this.userService = userService;
    }

    public List<User> listarFuncionariosDaFilial(Long filialId, Long gestorId) {
        return userService.findByFilialId(filialId)
                .stream()
                .filter(u -> !u.getId().equals(gestorId))
                .toList();
    }
}
