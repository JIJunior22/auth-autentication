package io.projeto.security.auth.autenticacao.services;

import io.projeto.security.auth.autenticacao.dto.AuthDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AutenticacaoServiceImpl extends UserDetailsService {
    public String obterToken(AuthDTO authDTO);
    public String validaToken(String token);

    //Este extends permite a classe AutenticacaoService utilizar o método sobrecarregado: loadUserByUsername
}
