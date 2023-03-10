package br.com.cwi.crescer.desafiosec2.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Funcao {

    USUARIO(Nomes.USUARIO),
    ADMIN(Nomes.ADMIN);

    private final String role;

    public static class Nomes {

        public static final String USUARIO = "ROLE_USUARIO";
        public static final String ADMIN = "ROLE_ADMIN";
    }
}
