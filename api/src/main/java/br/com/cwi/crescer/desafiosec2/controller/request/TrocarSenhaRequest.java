package br.com.cwi.crescer.desafiosec2.controller.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class TrocarSenhaRequest {

    @Email(message = "O email deve ser válido")
    @NotBlank(message = "O email não pode ser vazio")
    private String emailNovaSenha;

    @NotBlank(message = "O codigo não pode ser vazio")
    private String codigo;

    @NotBlank(message = "A nova senha não pode ser vazia")
    private String novaSenha;

}
