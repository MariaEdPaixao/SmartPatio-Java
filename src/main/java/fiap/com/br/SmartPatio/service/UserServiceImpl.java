package fiap.com.br.SmartPatio.service;

import fiap.com.br.SmartPatio.datasource.repository.UserRepository;
import fiap.com.br.SmartPatio.domainmodel.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UserRepository usuarioRepository;

    public UsuarioServiceImpl(UserRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public User salvar(User usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<User> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public List<User> listarTodos() {
        return usuarioRepository.findAll();
    }
}
