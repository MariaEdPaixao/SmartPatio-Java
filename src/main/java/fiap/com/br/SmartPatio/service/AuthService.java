package fiap.com.br.SmartPatio.service;

import fiap.com.br.SmartPatio.controller.dto.ResetPasswordDTO;
import fiap.com.br.SmartPatio.domainmodel.User;

public interface AuthService {
    boolean registerUser(User user);
    boolean resetPassword(ResetPasswordDTO dto);
}
