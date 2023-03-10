package br.com.cwi.crescer.desafiosec2.service.usuario;

import br.com.cwi.crescer.desafiosec2.controller.response.UsuarioResponse;
import br.com.cwi.crescer.desafiosec2.mapper.UsuarioMapper;
import br.com.cwi.crescer.desafiosec2.repository.UsuarioJpaRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListarUsuariosService {

    @Autowired
    private UsuarioJpaRepository usuarioJpaRepository;

    public List<UsuarioResponse> listar() {

        return usuarioJpaRepository.findAll().stream()
            .map(UsuarioMapper::toResponse)
            .collect(Collectors.toList());
    }
}
