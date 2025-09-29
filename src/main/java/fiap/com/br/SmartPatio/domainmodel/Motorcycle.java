package fiap.com.br.SmartPatio.domainmodel;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "moto")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Motorcycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @NotBlank(message = "A plca é obrigatória")
    @Size(max = 7, message = "A placa deve ter no máximo 7 caracteres")
    @Getter @Setter
    private String placa;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_modelo_moto", nullable = false)
    @NotNull(message = "O modelo da moto é obrigatório")
    @Getter @Setter
    private MotorcycleModel modelo;

}
