package fiap.com.br.SmartPatio.service;

import fiap.com.br.SmartPatio.domainmodel.Carrapato;

import java.util.List;

public interface CarrapatoService {
    List<Carrapato> findTop5ByOrderByNivelBateriaAsc();
}
