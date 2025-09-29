package fiap.com.br.SmartPatio.domainmodel;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "modelo_moto")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class MotorcycleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @NotBlank(message = "O modelo é obrigatório")
    @Size(max = 50, message = "O modelo deve ter no máximo 50 caracteres")
    @Getter
    @Setter
    private String modelo;
}
