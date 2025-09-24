package fiap.com.br.SmartPatio.datasource.repository;

import fiap.com.br.SmartPatio.domainmodel.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByEmail(String email);
}
