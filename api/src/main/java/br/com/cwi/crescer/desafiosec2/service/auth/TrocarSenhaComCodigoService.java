package br.com.cwi.crescer.desafiosec2.service.auth;

import br.com.cwi.crescer.desafiosec2.controller.request.TrocarSenhaRequest;
import br.com.cwi.crescer.desafiosec2.domain.Usuario;
import br.com.cwi.crescer.desafiosec2.repository.UsuarioJpaRepository;
import br.com.cwi.crescer.desafiosec2.service.usuario.BuscarUsuarioService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TrocarSenhaComCodigoService {

    @Autowired
    private UsuarioJpaRepository usuarioJpaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    public void trocarSenha(TrocarSenhaRequest trocarSenhaRequest) {

        if (usuarioJpaRepository.existsByEmail(trocarSenhaRequest.getEmailNovaSenha())){
            Usuario usuario = buscarUsuarioService.porEmail(trocarSenhaRequest.getEmailNovaSenha());

            if (!passwordEncoder.matches(trocarSenhaRequest.getCodigo().trim(), usuario.getCodigoSenha()) ||
                usuario.getExpiracaoCodigo().isBefore(LocalDateTime.now())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Código invalido");
            }

            usuario.setSenha(passwordEncoder.encode(trocarSenhaRequest.getNovaSenha()));
            usuario.setCodigoSenha(null);
            usuario.setExpiracaoCodigo(null);
            usuarioJpaRepository.save(usuario);
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Código invalido");
        }
    }
}
