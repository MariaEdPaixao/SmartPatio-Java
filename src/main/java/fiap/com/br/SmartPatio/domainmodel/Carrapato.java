package fiap.com.br.SmartPatio.domainmodel;

import fiap.com.br.SmartPatio.domainmodel.enums.CarrapatoStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "carrapato")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Carrapato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @NotBlank(message = "O Codigo serial do carrapato é obrigatório")
    @Size(max = 50, message = "O código serial deve ter no máximo 50 caracteres")
    @Column(name = "codigo_serial", nullable = false, unique = true)
    @Getter @Setter
    private String codigoSerial;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O status do carrapato é obrigatório")
    @Getter @Setter
    private CarrapatoStatus status;

    @Min(value = 0, message = "O nível da bateria não pode ser menor que 0")
    @Max(value = 100, message = "O nível da bateria não pode ser maior que 100")
    @Column(name = "nivel_bateria", nullable = false)
    @Getter @Setter
    private int nivelBateria = 100;
}

