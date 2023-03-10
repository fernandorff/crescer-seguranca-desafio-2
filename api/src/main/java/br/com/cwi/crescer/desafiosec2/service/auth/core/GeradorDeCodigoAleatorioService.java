package br.com.cwi.crescer.desafiosec2.service.auth.core;

import java.security.SecureRandom;
import java.util.Base64;
import org.springframework.stereotype.Service;

@Service
public class GeradorDeCodigoAleatorioService {

    private static final int CODE_LENGTH = 6;
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public String gerarCodigo() {
        byte[] codeBytes = new byte[CODE_LENGTH];
        SECURE_RANDOM.nextBytes(codeBytes);
        String code = Base64.getUrlEncoder().withoutPadding().encodeToString(codeBytes);
        return code.substring(0, CODE_LENGTH);
    }
}