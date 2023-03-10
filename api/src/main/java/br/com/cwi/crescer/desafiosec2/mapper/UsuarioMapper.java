package br.com.cwi.crescer.desafiosec2.mapper;

import static java.util.Objects.isNull;

import br.com.cwi.crescer.desafiosec2.controller.request.UsuarioRequest;
import br.com.cwi.crescer.desafiosec2.controller.response.UsuarioResponse;
import br.com.cwi.crescer.desafiosec2.domain.Usuario;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequest request) {
        Usuario entity = new Usuario();
        entity.setNomeCompleto(request.getNomeCompleto());
        entity.setTelefone(request.getTelefone());
        entity.setEmail(request.getEmail());
        return entity;
    }

    public static UsuarioResponse toResponse(Usuario entity) {

        if (isNull(entity)) {
            return UsuarioResponse.builder().build();
        }

        UsuarioResponse response = new UsuarioResponse();
        response.setId(entity.getId());
        response.setNomeCompleto(entity.getNomeCompleto());
        response.setTelefone(entity.getTelefone());
        response.setEmail(entity.getEmail());
        response.setCriadoEm(entity.getCriadoEm());
        response.setFotoUrl(entity.getFotoUrl());
        response.setAtualizadoEm(entity.getAtualizadoEm());
        response.setAtivo(entity.getAtivo());
        return response;
    }


}
