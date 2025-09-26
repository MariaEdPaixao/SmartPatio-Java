package fiap.com.br.SmartPatio.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

public class UpdateUserDTO {
    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    @Getter
    @Setter
    private String nome;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "O email deve ser válido")
    @Size(max = 100, message = "O email deve ter no máximo 100 caracteres")
    @Getter @Setter
    private String email;

    @Pattern(regexp = "^$|.{6,}", message = "A senha deve ter no mínimo 6 caracteres")
    @Getter @Setter
    private String senha;
}
