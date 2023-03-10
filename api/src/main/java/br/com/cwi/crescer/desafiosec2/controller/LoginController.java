package br.com.cwi.crescer.desafiosec2.controller;

import br.com.cwi.crescer.desafiosec2.controller.response.UsuarioResponse;
import br.com.cwi.crescer.desafiosec2.service.auth.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @PostMapping
    public UsuarioResponse login() {
        return usuarioAutenticadoService.getResponse();
    }
}
