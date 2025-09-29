package fiap.com.br.SmartPatio.service;
import fiap.com.br.SmartPatio.domainmodel.HistoricMotorcycleFilial;

public interface MotorcycleEntryExitService {
    HistoricMotorcycleFilial registerEntry(Long filialId, Long usuarioId);
    HistoricMotorcycleFilial registerExit(Long motoId);
    HistoricMotorcycleFilial registerExitAleatoria(Long filialId, Long usuarioId);
}
