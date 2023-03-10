package br.com.cwi.crescer.desafiosec2.controller.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditarUsuarioRequest {

    @NotBlank(message = "O nome completo não pode ser vazio")
    private String nomeCompleto;

    @NotBlank(message = "O telefone não pode ser vazio")
    private String telefone;

    private String fotoUrl;
}

