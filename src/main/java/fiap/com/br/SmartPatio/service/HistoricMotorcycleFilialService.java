package fiap.com.br.SmartPatio.service;

import fiap.com.br.SmartPatio.domainmodel.Filial;
import fiap.com.br.SmartPatio.domainmodel.HistoricMotorcycleFilial;
import fiap.com.br.SmartPatio.domainmodel.enums.HistoricMotorcycleStatus;

import java.util.List;

public interface HistoricMotorcycleFilialService {
    List<HistoricMotorcycleFilial> findByStatusAndFilial(HistoricMotorcycleStatus status, Filial filial);
}
