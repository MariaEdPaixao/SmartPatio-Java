package fiap.com.br.SmartPatio.service;

import fiap.com.br.SmartPatio.datasource.repository.CarrapatoRepository;
import fiap.com.br.SmartPatio.datasource.repository.HistoricMotorcycleFilialRepository;
import fiap.com.br.SmartPatio.datasource.repository.MotorcycleRepository;
import fiap.com.br.SmartPatio.domainmodel.*;
import fiap.com.br.SmartPatio.domainmodel.enums.CarrapatoStatus;
import fiap.com.br.SmartPatio.domainmodel.enums.HistoricMotorcycleStatus;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class MotorcycleEntryExitServiceImpl implements MotorcycleEntryExitService {

    private final MotorcycleRepository motorcycleRepository;
    private final CarrapatoRepository carrapatoRepository;
    private final HistoricMotorcycleFilialRepository historicoRepository;

    public MotorcycleEntryExitServiceImpl(MotorcycleRepository motorcycleRepository,
                                          CarrapatoRepository carrapatoRepository,
                                          HistoricMotorcycleFilialRepository historicoRepository) {
        this.motorcycleRepository = motorcycleRepository;
        this.carrapatoRepository = carrapatoRepository;
        this.historicoRepository = historicoRepository;
    }

    @Override
    @Transactional
    public HistoricMotorcycleFilial registerEntry(Long filialId, Long usuarioId) {
        Motorcycle moto = buscarMotoDisponivel();
        Carrapato carrapato = buscarCarrapatoDisponivel();

        HistoricMotorcycleFilial historico = criarHistoricoEntrada(moto, carrapato, filialId);
        atualizarStatusCarrapato(carrapato, CarrapatoStatus.EM_USO);

        persistirHistoricoComCarrapato(historico, carrapato);
        return historico;
    }

    @Override
    @Transactional
    public HistoricMotorcycleFilial registerExit(Long motoId) {
        HistoricMotorcycleFilial historico = buscarHistoricoAtivo(motoId);
        atualizarHistoricoSaida(historico);
        atualizarStatusCarrapato(historico.getCarrapato(), CarrapatoStatus.DISPONIVEL);

        persistirHistoricoComCarrapato(historico, historico.getCarrapato());
        return historico;
    }

    @Override
    @Transactional
    public HistoricMotorcycleFilial registerExitAleatoria(Long filialId, Long usuarioId) {
        List<HistoricMotorcycleFilial> ativos = historicoRepository.findByStatusAndFilial(
                HistoricMotorcycleStatus.ATIVA, new Filial(filialId));

        if (ativos.isEmpty()) {
            throw new IllegalStateException("Nenhuma moto ativa no pátio da filial");
        }

        HistoricMotorcycleFilial historico = escolherAleatorio(ativos);
        atualizarHistoricoSaida(historico);
        atualizarStatusCarrapato(historico.getCarrapato(), CarrapatoStatus.DISPONIVEL);

        persistirHistoricoComCarrapato(historico, historico.getCarrapato());
        return historico;
    }

    private Motorcycle buscarMotoDisponivel() {
        List<Motorcycle> disponiveis = motorcycleRepository.findMotorcyclesWithoutFilial();
        if (disponiveis.isEmpty()) {
            throw new IllegalStateException("Nenhuma moto disponível");
        }
        return escolherAleatorio(disponiveis);
    }

    private <T> T escolherAleatorio(List<T> lista) {
        int index = new Random().nextInt(lista.size());
        return lista.get(index);
    }


    private Carrapato buscarCarrapatoDisponivel() {
        return carrapatoRepository.findFirstByStatus(CarrapatoStatus.DISPONIVEL)
                .orElseThrow(() -> new IllegalStateException("Nenhum carrapato disponível"));
    }

    private HistoricMotorcycleFilial criarHistoricoEntrada(Motorcycle moto, Carrapato carrapato, Long filialId) {
        HistoricMotorcycleFilial historico = new HistoricMotorcycleFilial();
        historico.setMoto(moto);
        historico.setFilial(new Filial(filialId));
        historico.setCarrapato(carrapato);
        historico.setStatus(HistoricMotorcycleStatus.ATIVA);
        historico.setDataEntrada(LocalDateTime.now());
        return historico;
    }

    private HistoricMotorcycleFilial buscarHistoricoAtivo(Long motoId) {
        Motorcycle moto = motorcycleRepository.findById(motoId)
                .orElseThrow(() -> new IllegalArgumentException("Moto não encontrada"));

        return historicoRepository.findByMotoAndStatus(moto, HistoricMotorcycleStatus.ATIVA)
                .orElseThrow(() -> new IllegalStateException("Histórico ativo não encontrado"));
    }

    private void atualizarHistoricoSaida(HistoricMotorcycleFilial historico) {
        historico.setStatus(HistoricMotorcycleStatus.FINALIZADA);
        historico.setDataSaida(LocalDateTime.now());
    }

    private void atualizarStatusCarrapato(Carrapato carrapato, CarrapatoStatus status) {
        carrapato.setStatus(status);
    }

    private void persistirHistoricoComCarrapato(HistoricMotorcycleFilial historico, Carrapato carrapato) {
        historicoRepository.save(historico);
        carrapatoRepository.save(carrapato);
    }
}
