package fiap.com.br.SmartPatio.datasource.repository;

import fiap.com.br.SmartPatio.domainmodel.Carrapato;
import fiap.com.br.SmartPatio.domainmodel.enums.CarrapatoStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarrapatoRepository extends JpaRepository<Carrapato, Long> {
    Optional<Carrapato> findFirstByStatus(CarrapatoStatus status);
    List<Carrapato> findTop5ByOrderByNivelBateriaAsc();
}
