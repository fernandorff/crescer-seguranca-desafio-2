package br.com.cwi.crescer.desafiosec2.repository;

import br.com.cwi.crescer.desafiosec2.domain.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioJpaRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    List<Usuario> findByNomeCompletoContainingIgnoreCaseOrEmailContainingIgnoreCase(String query,
        String query1);

    boolean existsByEmail(String email);

}
