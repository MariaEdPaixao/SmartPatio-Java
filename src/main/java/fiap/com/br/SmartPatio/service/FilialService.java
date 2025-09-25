package fiap.com.br.SmartPatio.service;

import fiap.com.br.SmartPatio.domainmodel.Filial;
import java.util.List;

public interface FilialService {
    List<Filial> findAll();
    Filial findById(Long id);
}
