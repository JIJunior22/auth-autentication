package io.projeto.security.auth.autenticacao.config;

import io.projeto.security.auth.autenticacao.domain.Usuario;
import io.projeto.security.auth.autenticacao.repository.UsuarioRepository;
import io.projeto.security.auth.autenticacao.services.AutenticacaoService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String token = extraiTokenHeader(request);
        if (token != null) {
            String login = autenticacaoService.validaToken(token);
            Usuario user = repository.findByLogin(login);

            var autentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(autentication);


        }
        filterChain.doFilter(request, response);


    }

    public String extraiTokenHeader(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            return null;
        }
        if (!authHeader.split("")[0].equals("Bearer")) {
            return null;
        }
        return authHeader.split("")[1];


    }
}
