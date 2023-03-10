package br.com.cwi.crescer.desafiosec2.config.exception.handler;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(
        MethodArgumentNotValidException ex,
        HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        String mensagem = extrairErro(ex);

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", mensagem);
        body.put("path", request.getServletPath());

        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatusException(
        ResponseStatusException ex,
        HttpServletRequest request) {

        HttpStatus status = ex.getStatus();
        String mensagem = ex.getReason();

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", mensagem);
        body.put("path", request.getServletPath());

        return new ResponseEntity<>(body, status);
    }

    private String extrairErro(MethodArgumentNotValidException ex) {
        Optional<ObjectError> erroOpt = ex.getBindingResult().getAllErrors().stream()
            .findFirst();

        if (erroOpt.isEmpty()) {
            return "erro de valida????o";
        }

        FieldError erro = (FieldError) erroOpt.get();

        return erro.getField() + ": " + erro.getDefaultMessage();
    }

    private String now() {
        return LocalDateTime.now().toString();
    }
}
