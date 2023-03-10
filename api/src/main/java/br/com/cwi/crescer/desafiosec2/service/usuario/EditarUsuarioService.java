package br.com.cwi.crescer.desafiosec2.service.usuario;

import br.com.cwi.crescer.desafiosec2.controller.request.EditarUsuarioRequest;
import br.com.cwi.crescer.desafiosec2.domain.Usuario;
import br.com.cwi.crescer.desafiosec2.repository.UsuarioJpaRepository;
import br.com.cwi.crescer.desafiosec2.service.auth.core.NowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditarUsuarioService {

    @Autowired
    UsuarioJpaRepository usuarioJpaRepository;

    @Autowired
    BuscarUsuarioService buscarUsuarioService;

    @Autowired
    NowService nowService;

    public void editarUsuario(Long usuarioId, EditarUsuarioRequest request) {

        Usuario usuario = buscarUsuarioService.porId(usuarioId);

        usuario.setNomeCompleto(request.getNomeCompleto());
        usuario.setTelefone(request.getTelefone());
        usuario.setFotoUrl(request.getFotoUrl());
        usuario.setAtualizadoEm(nowService.getDateTime());

        usuarioJpaRepository.save(usuario);
    }
}
