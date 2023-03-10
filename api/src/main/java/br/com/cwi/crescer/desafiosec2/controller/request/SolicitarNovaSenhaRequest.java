package br.com.cwi.crescer.desafiosec2.controller.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SolicitarNovaSenhaRequest {

    @Email(message = "O email deve ser válido")
    @NotBlank(message = "O email não pode ser vazio")
    private String emailNovaSenha;


}
