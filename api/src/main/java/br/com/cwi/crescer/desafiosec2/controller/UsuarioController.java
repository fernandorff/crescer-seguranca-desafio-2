package br.com.cwi.crescer.desafiosec2.controller;

import br.com.cwi.crescer.desafiosec2.config.BuscarUsuarioSecuritySerivce;
import br.com.cwi.crescer.desafiosec2.config.UsuarioSecurity;
import br.com.cwi.crescer.desafiosec2.controller.request.EditarUsuarioRequest;
import br.com.cwi.crescer.desafiosec2.controller.request.SolicitarNovaSenhaRequest;
import br.com.cwi.crescer.desafiosec2.controller.request.TrocarSenhaRequest;
import br.com.cwi.crescer.desafiosec2.controller.request.UsuarioRequest;
import br.com.cwi.crescer.desafiosec2.controller.response.UsuarioResponse;
import br.com.cwi.crescer.desafiosec2.domain.Usuario;
import br.com.cwi.crescer.desafiosec2.mapper.UsuarioMapper;
import br.com.cwi.crescer.desafiosec2.repository.UsuarioJpaRepository;
import br.com.cwi.crescer.desafiosec2.service.auth.IncluirUsuarioService;
import br.com.cwi.crescer.desafiosec2.service.auth.SolicitarNovaSenhaService;
import br.com.cwi.crescer.desafiosec2.service.auth.TrocarSenhaComCodigoService;
import br.com.cwi.crescer.desafiosec2.service.usuario.BuscarUsuarioService;
import br.com.cwi.crescer.desafiosec2.service.usuario.DetalharUsuarioService;
import br.com.cwi.crescer.desafiosec2.service.usuario.EditarUsuarioService;
import br.com.cwi.crescer.desafiosec2.service.usuario.ListarUsuariosService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IncluirUsuarioService incluirUsuarioService;

    @Autowired
    private ListarUsuariosService listarUsuariosService;

    @Autowired
    private DetalharUsuarioService detalharUsuarioService;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @Autowired
    private EditarUsuarioService editarUsuarioService;

    @Autowired
    private UsuarioJpaRepository usuarioJpaRepository;

    @Autowired
    private BuscarUsuarioSecuritySerivce buscarUsuarioSecuritySerivce;

    @Autowired
    private SolicitarNovaSenhaService solicitarNovaSenhaService;

    @Autowired
    private TrocarSenhaComCodigoService trocarSenhaComCodigoService;

    @PostMapping
    public UsuarioResponse cadastrar(@Valid @RequestBody UsuarioRequest request) {
        return incluirUsuarioService.incluir(request);
    }

    @GetMapping
    public List<UsuarioResponse> listar() {
        return listarUsuariosService.listar();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/novasenha/publico")
    public void solicitarNovaSenha(
        @Valid @RequestBody SolicitarNovaSenhaRequest solicitarNovaSenhaRequest) {
        solicitarNovaSenhaService.enviarEmailCodigo(solicitarNovaSenhaRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/trocarsenha/publico")
    public void trocarSenha(@Valid @RequestBody TrocarSenhaRequest trocarSenhaRequest) {
        trocarSenhaComCodigoService.trocarSenha(trocarSenhaRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{usuarioId}")
    public UsuarioResponse detalhar(@PathVariable Long usuarioId) {
        return detalharUsuarioService.detalhar(usuarioId);
    }

    @GetMapping("/me")
    public UsuarioResponse getCurrentUser(@AuthenticationPrincipal UsuarioSecurity userDetails) {
        Usuario user = usuarioJpaRepository.findByEmail(userDetails.getUsername())
            .orElseThrow(() -> new UsernameNotFoundException(
                "User not found with username: " + userDetails.getUsername()));
        return UsuarioMapper.toResponse(user);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("{usuarioId}")
    public void editarUsuario(@PathVariable Long usuarioId,
        @Valid @RequestBody EditarUsuarioRequest request) {
        editarUsuarioService.editarUsuario(usuarioId, request);
    }
}
