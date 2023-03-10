package br.com.cwi.crescer.desafiosec2.service.auth;

import static br.com.cwi.crescer.desafiosec2.mapper.UsuarioMapper.toResponse;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import br.com.cwi.crescer.desafiosec2.config.UsuarioSecurity;
import br.com.cwi.crescer.desafiosec2.controller.response.UsuarioResponse;
import br.com.cwi.crescer.desafiosec2.domain.Usuario;
import br.com.cwi.crescer.desafiosec2.repository.UsuarioJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioAutenticadoService {

    @Autowired
    private UsuarioJpaRepository usuarioJpaRepository;

    public Long getId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof UsuarioSecurity) {
            return ((UsuarioSecurity) authentication.getPrincipal()).getId();
        }

        return null;
    }

    public Usuario get() {
        Long id = getId();

        if (isNull(id)) {
            return null;
        }

        return usuarioJpaRepository.findById(getId()).orElse(null);
    }

    public UsuarioResponse getResponse() {
        Usuario entity = get();
        return nonNull(entity) ? toResponse(entity) : new UsuarioResponse();
    }
}
