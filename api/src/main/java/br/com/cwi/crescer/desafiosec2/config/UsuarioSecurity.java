package br.com.cwi.crescer.desafiosec2.config;

import br.com.cwi.crescer.desafiosec2.domain.Usuario;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
public class UsuarioSecurity implements UserDetails {

    private final Long id;
    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;

    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;

    public UsuarioSecurity(Usuario usuario) {

        this.id = usuario.getId();
        this.username = usuario.getEmail();
        this.password = usuario.getSenha();

        this.accountNonExpired = usuario.getAtivo();
        this.accountNonLocked = usuario.getAtivo();
        this.credentialsNonExpired = usuario.getAtivo();
        this.enabled = usuario.getAtivo();

        this.authorities = usuario.getPermissoes().stream()
            .map(permissao -> new SimpleGrantedAuthority(permissao.getFuncao().getRole()))
            .collect(Collectors.toList());
    }
}
