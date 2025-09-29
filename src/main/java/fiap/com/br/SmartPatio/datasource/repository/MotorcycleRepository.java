package fiap.com.br.SmartPatio.datasource.repository;

import fiap.com.br.SmartPatio.domainmodel.Motorcycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MotorcycleRepository extends JpaRepository<Motorcycle, Long> {
    @Query("SELECT m FROM Motorcycle m WHERE NOT EXISTS " +
            "(SELECT h FROM HistoricMotorcycleFilial h WHERE h.moto = m AND h.status = 'ATIVA')")
    List<Motorcycle> findMotorcyclesWithoutFilial();
}

