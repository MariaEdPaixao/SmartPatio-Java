package fiap.com.br.SmartPatio.domainmodel;

import fiap.com.br.SmartPatio.domainmodel.enums.HistoricMotorcycleStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "historico_moto_filial")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class HistoricMotorcycleFilial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_moto", nullable = false)
    @NotNull(message = "A moto é obrigatória")
    @Getter @Setter
    private Motorcycle moto;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_filial", nullable = false)
    @NotNull(message = "A filial é obrigatória")
    @Getter @Setter
    private Filial filial;

    @ManyToOne
    @JoinColumn(name = "id_carrapato")
    @Getter @Setter
    private Carrapato carrapato;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    @Getter @Setter
    private User usuario;

    @Column(name = "data_entrada", nullable = false, updatable = false)
    @Getter @Setter
    private LocalDateTime dataEntrada = LocalDateTime.now();

    @Column(name = "data_saida")
    @Getter @Setter
    private LocalDateTime dataSaida;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O status do histórico é obrigatório")
    @Getter @Setter
    private HistoricMotorcycleStatus status;
}
