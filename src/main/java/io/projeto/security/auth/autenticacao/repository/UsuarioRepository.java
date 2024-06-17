package io.projeto.security.auth.autenticacao.repository;

import io.projeto.security.auth.autenticacao.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByLogin(String login);


}
