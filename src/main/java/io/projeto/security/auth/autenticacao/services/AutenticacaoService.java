package io.projeto.security.auth.autenticacao.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import io.projeto.security.auth.autenticacao.domain.Usuario;
import io.projeto.security.auth.autenticacao.dto.AuthDTO;
import io.projeto.security.auth.autenticacao.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class AutenticacaoService implements AutenticacaoServiceImpl {
    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return repository.findByLogin(login);
    }

    @Override
    public String obterToken(AuthDTO authDTO) {
        Usuario usuario = repository.findByLogin(authDTO.login());
        return gerarToken(usuario);
    }

    public String gerarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("my-secret");

            return JWT.create()
                    .withIssuer("auth-autenticacao")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(geraDataExpiracao())
                    .sign(algorithm);

        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao tentar gerar token:" + e.getMessage());

        }

    }

    private Instant geraDataExpiracao() {
        return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-03:00"));
    }
    @Override
    public String validaToken(String token) {
        try{
            Algorithm algorithm=Algorithm.HMAC256("my-secret");
            return JWT
                    .require(algorithm)
                    .withIssuer("auth-autenticacao")
                    .build()
                    .verify(token)
                    .getSubject();

        }catch(JWTVerificationException e){
            throw new RuntimeException("Erro ao tentar validar token:" + e.getMessage());

        }
    }
}
