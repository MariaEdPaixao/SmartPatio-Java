package fiap.com.br.SmartPatio.service;

import fiap.com.br.SmartPatio.controller.dto.UpdateUserDTO;
import fiap.com.br.SmartPatio.domainmodel.User;
import fiap.com.br.SmartPatio.domainmodel.enums.HistoricMotorcycleStatus;

public interface ProfileService {
    User getUsuarioLogado(String email);
    int countBranchEmployees(Long filialId);
    int countActiveMotorcycles(User user);
    void updateProfile(User user, UpdateUserDTO dto);
    void deleteUser(User user);
}
