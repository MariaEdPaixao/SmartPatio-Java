package fiap.com.br.SmartPatio.service;

import fiap.com.br.SmartPatio.controller.dto.ResetPassordDTO;
import fiap.com.br.SmartPatio.domainmodel.User;

public interface AuthService {
    void registerUser(User user);
    boolean resetPassword(ResetPassordDTO dto);
}
