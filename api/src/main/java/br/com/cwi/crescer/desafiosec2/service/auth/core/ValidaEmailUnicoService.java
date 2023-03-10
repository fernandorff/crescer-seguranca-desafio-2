package br.com.cwi.crescer.desafiosec2.service.auth.core;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import br.com.cwi.crescer.desafiosec2.repository.UsuarioJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ValidaEmailUnicoService {

    @Autowired
    private UsuarioJpaRepository repository;

    public void validar(String email) {

        boolean existeOutroUsuarioComMesmoEmail = repository.existsByEmail(email);

        if (existeOutroUsuarioComMesmoEmail) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY,
                "Email do usuario deve ser único");
        }
    }
}
