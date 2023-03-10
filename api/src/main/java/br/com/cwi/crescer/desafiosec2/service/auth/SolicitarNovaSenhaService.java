package br.com.cwi.crescer.desafiosec2.service.auth;

import br.com.cwi.crescer.desafiosec2.controller.request.SolicitarNovaSenhaRequest;
import br.com.cwi.crescer.desafiosec2.domain.Usuario;
import br.com.cwi.crescer.desafiosec2.repository.UsuarioJpaRepository;
import br.com.cwi.crescer.desafiosec2.service.auth.core.GeradorDeCodigoAleatorioService;
import br.com.cwi.crescer.desafiosec2.service.usuario.BuscarUsuarioService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class SolicitarNovaSenhaService {

    private static final String ASSUNTO = "Recuperação de senha CrescerSec";
    private static final String TEXTO = "Seu código para recuperação de senha se encontra abaixo e tem duração de 10 minutos.\n\n";
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private UsuarioJpaRepository usuarioJpaRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private GeradorDeCodigoAleatorioService geradorDeCodigoAleatorioService;
    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    public void enviarEmailCodigo(SolicitarNovaSenhaRequest solicitarNovaSenhaRequest) {

        if (usuarioJpaRepository.existsByEmail(solicitarNovaSenhaRequest.getEmailNovaSenha())){
            Usuario usuario = buscarUsuarioService.porEmail(
                solicitarNovaSenhaRequest.getEmailNovaSenha());

            String codigo = geradorDeCodigoAleatorioService.gerarCodigo();

            usuario.setCodigoSenha(passwordEncoder.encode(codigo));
            usuario.setExpiracaoCodigo(LocalDateTime.now().plusMinutes(10));

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(usuario.getEmail());
            message.setSubject(ASSUNTO);
            message.setText(TEXTO + codigo);
            javaMailSender.send(message);

            usuarioJpaRepository.save(usuario);
        }
    }
}
