package br.com.cwi.crescer.desafiosec2.service.auth;

import static br.com.cwi.crescer.desafiosec2.mapper.UsuarioMapper.toEntity;
import static br.com.cwi.crescer.desafiosec2.mapper.UsuarioMapper.toResponse;

import br.com.cwi.crescer.desafiosec2.controller.request.UsuarioRequest;
import br.com.cwi.crescer.desafiosec2.controller.response.UsuarioResponse;
import br.com.cwi.crescer.desafiosec2.domain.Permissao;
import br.com.cwi.crescer.desafiosec2.domain.Usuario;
import br.com.cwi.crescer.desafiosec2.domain.enums.Funcao;
import br.com.cwi.crescer.desafiosec2.repository.UsuarioJpaRepository;
import br.com.cwi.crescer.desafiosec2.service.auth.core.NowService;
import br.com.cwi.crescer.desafiosec2.service.auth.core.ValidaEmailUnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IncluirUsuarioService {

    @Autowired
    private UsuarioJpaRepository usuarioJpaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private NowService nowService;

    @Autowired
    private ValidaEmailUnicoService validaEmailUnicoService;

    @Transactional
    public UsuarioResponse incluir(UsuarioRequest request) {

        validaEmailUnicoService.validar(request.getEmail());

        Usuario usuario = toEntity(request);
        usuario.setSenha(getSenhaCriptografada(request.getSenha()));
        usuario.adicionarPermissao(getPermissaoPadrao());
        usuario.setAtivo(true);
        usuario.setCriadoEm(nowService.getDateTime());
        usuarioJpaRepository.save(usuario);
        return toResponse(usuario);
    }

    private String getSenhaCriptografada(String senhaAberta) {
        return passwordEncoder.encode(senhaAberta);
    }

    private Permissao getPermissaoPadrao() {
        Permissao permissao = new Permissao();
        permissao.setFuncao(Funcao.USUARIO);
        return permissao;
    }
}
