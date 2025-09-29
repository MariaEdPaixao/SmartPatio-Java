package fiap.com.br.SmartPatio.service;

import fiap.com.br.SmartPatio.controller.dto.UpdateUserDTO;
import fiap.com.br.SmartPatio.domainmodel.User;
import fiap.com.br.SmartPatio.domainmodel.enums.HistoricMotorcycleStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService{

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final HistoricMotorcycleFilialService historicoService;

    public ProfileServiceImpl(UserService userService,
                          PasswordEncoder passwordEncoder,
                          HistoricMotorcycleFilialService historicoService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.historicoService = historicoService;
    }

    @Override
    public User getUsuarioLogado(String email) {
        return userService.findByEmailOrThrow(email);
    }
    @Override
    public int contarFuncionariosDaFilial(Long filialId) {
        return (int) userService.countByFilial(filialId);
    }
    @Override
    public int contarMotosAtivas(User user) {
        return historicoService.findByStatusAndFilial(HistoricMotorcycleStatus.ATIVA, user.getFilial()).size();
    }
    @Override
    public void atualizarPerfil(User user, UpdateUserDTO dto) {
        user.setNome(dto.getNome());
        user.setEmail(dto.getEmail());

        if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
            user.setSenha(passwordEncoder.encode(dto.getSenha()));
        }

        userService.save(user);
    }
    @Override
    public void excluirUsuario(User user) {
        userService.deleteById(user.getId());
    }
}
