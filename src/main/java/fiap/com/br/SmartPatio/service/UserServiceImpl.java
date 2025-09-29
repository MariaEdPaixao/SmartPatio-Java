package fiap.com.br.SmartPatio.service;

import fiap.com.br.SmartPatio.datasource.repository.UserRepository;
import fiap.com.br.SmartPatio.domainmodel.User;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository usuarioRepository;

    public UserServiceImpl(UserRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public User save(User usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return usuarioRepository.findAll();
    }
    @Override
    public long countByFilial(Long filialId) {
        return usuarioRepository.countByFilial_Id(filialId);
    }

    @Override
    public void deleteById(Long id){
        usuarioRepository.deleteById(id);
    }

    @Override
    public List<User> findByFilialId(Long filialId) {
        return usuarioRepository.findByFilialId(filialId);
    }
}
