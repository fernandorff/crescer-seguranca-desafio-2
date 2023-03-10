package br.com.cwi.crescer.desafiosec2.service.usuario;

import br.com.cwi.crescer.desafiosec2.domain.Usuario;
import br.com.cwi.crescer.desafiosec2.repository.UsuarioJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BuscarUsuarioService {

    @Autowired
    private UsuarioJpaRepository usuarioJpaRepository;

    public Usuario porId(Long usuarioId) {

        return usuarioJpaRepository.findById(usuarioId)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }

    public Usuario porEmail(String usuarioEmail) {

        return usuarioJpaRepository.findByEmail(usuarioEmail)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }
}
