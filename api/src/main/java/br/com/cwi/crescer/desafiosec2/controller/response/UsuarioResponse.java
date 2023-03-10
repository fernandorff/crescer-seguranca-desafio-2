package br.com.cwi.crescer.desafiosec2.controller.response;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@JsonInclude(NON_NULL)
public class UsuarioResponse {

    private Long id;
    private String nomeCompleto;
    private String telefone;
    private String email;
    private String fotoUrl;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
    private Boolean ativo;
}
