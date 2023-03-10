package br.com.cwi.crescer.desafiosec2.service.auth.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class NowService {

    public LocalDate getDate() {
        return LocalDate.now();
    }

    public LocalDateTime getDateTime() {
        return LocalDateTime.now();
    }

}
