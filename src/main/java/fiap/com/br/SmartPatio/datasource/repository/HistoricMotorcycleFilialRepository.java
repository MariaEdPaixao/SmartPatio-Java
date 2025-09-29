package fiap.com.br.SmartPatio.datasource.repository;

import fiap.com.br.SmartPatio.domainmodel.Filial;
import fiap.com.br.SmartPatio.domainmodel.HistoricMotorcycleFilial;
import fiap.com.br.SmartPatio.domainmodel.Motorcycle;
import fiap.com.br.SmartPatio.domainmodel.enums.HistoricMotorcycleStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HistoricMotorcycleFilialRepository extends JpaRepository<HistoricMotorcycleFilial, Long> {
    Optional<HistoricMotorcycleFilial> findByMotoAndStatus(Motorcycle moto, HistoricMotorcycleStatus status);
    List<HistoricMotorcycleFilial> findByFilialAndStatus(Filial filial, HistoricMotorcycleStatus status);
    List<HistoricMotorcycleFilial> findByStatusAndFilial(HistoricMotorcycleStatus status, Filial filial);
}
