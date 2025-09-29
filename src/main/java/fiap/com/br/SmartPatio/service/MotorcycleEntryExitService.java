package fiap.com.br.SmartPatio.service;
import fiap.com.br.SmartPatio.domainmodel.HistoricMotorcycleFilial;

public interface MotorcycleEntryExitService {
    public HistoricMotorcycleFilial registerEntry(Long filialId, Long usuarioId);
    public HistoricMotorcycleFilial registerExit(Long motoId);
    public HistoricMotorcycleFilial registerExitAleatoria(Long filialId, Long usuarioId);
}
