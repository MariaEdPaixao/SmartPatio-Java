package fiap.com.br.SmartPatio.service;

import fiap.com.br.SmartPatio.controller.dto.UpdateUserDTO;
import fiap.com.br.SmartPatio.domainmodel.User;
import fiap.com.br.SmartPatio.domainmodel.enums.HistoricMotorcycleStatus;

public interface ProfileService {
    User getUsuarioLogado(String email);
    int contarFuncionariosDaFilial(Long filialId);
    int contarMotosAtivas(User user);
    void atualizarPerfil(User user, UpdateUserDTO dto);
    void excluirUsuario(User user);
}
