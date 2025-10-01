package fiap.com.br.SmartPatio.service;

import fiap.com.br.SmartPatio.datasource.repository.UserRepository;
import fiap.com.br.SmartPatio.domainmodel.User;

import fiap.com.br.SmartPatio.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User usuario) {
        return userRepository.save(usuario);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
    @Override
    public long countByFilial(Long filialId) {
        return userRepository.countByFilial_Id(filialId);
    }

    @Override
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findByFilialId(Long filialId) {
        return userRepository.findByFilialId(filialId);
    }

    @Override
    public User findByEmailOrThrow(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }

}
