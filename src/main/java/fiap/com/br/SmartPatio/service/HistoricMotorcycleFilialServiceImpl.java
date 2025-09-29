package fiap.com.br.SmartPatio.service;

import fiap.com.br.SmartPatio.datasource.repository.HistoricMotorcycleFilialRepository;
import fiap.com.br.SmartPatio.domainmodel.Filial;
import fiap.com.br.SmartPatio.domainmodel.HistoricMotorcycleFilial;
import fiap.com.br.SmartPatio.domainmodel.enums.HistoricMotorcycleStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricMotorcycleFilialServiceImpl implements HistoricMotorcycleFilialService{
    private  final HistoricMotorcycleFilialRepository historicMotorcycleFilialRepository;

    public HistoricMotorcycleFilialServiceImpl(HistoricMotorcycleFilialRepository historicMotorcycleFilialRepository) {
        this.historicMotorcycleFilialRepository = historicMotorcycleFilialRepository;
    }

    @Override
    public List<HistoricMotorcycleFilial> findByStatusAndFilial(HistoricMotorcycleStatus status, Filial filial) {
        return historicMotorcycleFilialRepository.findByStatusAndFilial(status, filial);
    }
}
