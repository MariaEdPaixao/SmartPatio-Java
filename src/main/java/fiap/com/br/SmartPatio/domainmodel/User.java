package fiap.com.br.SmartPatio.domainmodel;

import fiap.com.br.SmartPatio.domainmodel.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuario")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

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

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    @Getter @Setter
    private String senha;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_filial", nullable = false)
    @NotNull(message = "A filial é obrigatória")
    @Getter @Setter
    private Filial filial;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O papel (role) é obrigatório")
    @Getter @Setter
    private UserRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.GESTOR) return List.of(new SimpleGrantedAuthority("ROLE_GESTOR"), new SimpleGrantedAuthority("ROLE_FUNCIONARIO"));
        else return List.of(new SimpleGrantedAuthority("ROLE_FUNCIONARIO"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
