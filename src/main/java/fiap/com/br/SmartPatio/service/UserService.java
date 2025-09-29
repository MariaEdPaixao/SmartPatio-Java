package fiap.com.br.SmartPatio.service;

import fiap.com.br.SmartPatio.domainmodel.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User usuario);
    Optional<User> findByEmail(String email);
    List<User> findAll();
    long countByFilial(Long filialId);
    void deleteById(Long id);
    List<User> findByFilialId(Long filialId);
    User findByEmailOrThrow(String email);
}