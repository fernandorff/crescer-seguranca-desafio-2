package br.com.cwi.crescer.desafiosec2.controller.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequest {

    @NotBlank(message = "O nome completo não pode ser vazio")
    private String nomeCompleto;

    @NotBlank(message = "O telefone não pode ser vazio")
    private String telefone;

    @Email(message = "O email deve ser válido")
    @NotBlank(message = "O email não pode ser vazio")
    private String email;

    @NotBlank(message = "A senha não pode ser vazia")
    private String senha;

    private String fotoUrl;
}

