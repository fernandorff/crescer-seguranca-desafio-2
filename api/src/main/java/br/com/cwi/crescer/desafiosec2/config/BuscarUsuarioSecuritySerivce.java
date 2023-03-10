package br.com.cwi.crescer.desafiosec2.config;

import br.com.cwi.crescer.desafiosec2.repository.UsuarioJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BuscarUsuarioSecuritySerivce implements UserDetailsService {

    @Autowired
    private UsuarioJpaRepository usuarioJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return usuarioJpaRepository.findByEmail(email)
            .map(UsuarioSecurity::new)
            .orElseThrow(() -> new UsernameNotFoundException("Credenciais inválidas"));
    }
}
