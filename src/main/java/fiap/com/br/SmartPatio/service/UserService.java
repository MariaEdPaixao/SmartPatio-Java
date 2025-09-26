package fiap.com.br.SmartPatio.service;

import fiap.com.br.SmartPatio.domainmodel.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User usuario);
    Optional<User> findByEmail(String email);
    List<User> findAll();
    public long countByFilial(Long filialId);
    public void deleteById(Long id);
}