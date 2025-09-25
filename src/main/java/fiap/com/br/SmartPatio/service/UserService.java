package fiap.com.br.SmartPatio.service;

import fiap.com.br.SmartPatio.domainmodel.User;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    User salvar(User usuario);
    Optional<User> buscarPorEmail(String email);
    List<User> listarTodos();
}