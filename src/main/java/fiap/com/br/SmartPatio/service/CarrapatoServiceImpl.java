package fiap.com.br.SmartPatio.service;

import fiap.com.br.SmartPatio.datasource.repository.CarrapatoRepository;
import fiap.com.br.SmartPatio.domainmodel.Carrapato;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrapatoServiceImpl implements CarrapatoService {

    private final CarrapatoRepository carrapatoRepository;

    public CarrapatoServiceImpl(CarrapatoRepository carrapatoRepository) {
        this.carrapatoRepository = carrapatoRepository;
    }


    @Override
    public List<Carrapato> findTop5ByOrderByNivelBateriaAsc() {
        return carrapatoRepository.findTop5ByOrderByNivelBateriaAsc();
    }
}
