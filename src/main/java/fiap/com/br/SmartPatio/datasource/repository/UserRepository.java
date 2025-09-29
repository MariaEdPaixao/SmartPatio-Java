package fiap.com.br.SmartPatio.datasource.repository;

import fiap.com.br.SmartPatio.domainmodel.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    long countByFilial_Id(Long filialId);
    List<User> findByFilialId(Long filialId);
}
