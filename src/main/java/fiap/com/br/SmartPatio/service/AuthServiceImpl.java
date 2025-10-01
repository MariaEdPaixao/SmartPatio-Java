package fiap.com.br.SmartPatio.service;

import fiap.com.br.SmartPatio.controller.dto.ResetPasswordDTO;
import fiap.com.br.SmartPatio.domainmodel.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final FilialService filialService;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserService userService, FilialService filialService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.filialService = filialService;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean registerUser(User user) {
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            return false;
        }

        Long filialId = user.getFilial().getId();
        user.setFilial(filialService.findById(filialId));
        user.setSenha(passwordEncoder.encode(user.getSenha()));
        userService.saveUser(user);
        return true;
    }

    public boolean resetPassword(ResetPasswordDTO dto) {
        return userService.findByEmail(dto.getEmail())
                .map(user -> {
                    user.setSenha(passwordEncoder.encode(dto.getSenha()));
                    userService.saveUser(user);
                    return true;
                })
                .orElse(false);
    }

}
