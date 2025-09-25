package fiap.com.br.SmartPatio.service;

import fiap.com.br.SmartPatio.datasource.repository.FilialRepository;
import fiap.com.br.SmartPatio.domainmodel.Filial;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilialServiceImpl implements FilialService{

    final FilialRepository filialRepository;

    public FilialServiceImpl(FilialRepository filialRepository) {

        this.filialRepository = filialRepository;
    }

    @Override
    public List<Filial> findAll() {
        return filialRepository.findAll();
    }

    @Override
    public Filial findById(Long id) {
        return filialRepository.findById(id).get();
    }
}
