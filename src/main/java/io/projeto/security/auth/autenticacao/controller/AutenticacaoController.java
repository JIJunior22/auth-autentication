package io.projeto.security.auth.autenticacao.controller;

import io.projeto.security.auth.autenticacao.dto.AuthDTO;
import io.projeto.security.auth.autenticacao.services.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String auth(@RequestBody AuthDTO authDTO) {
        var usuarioAutenticationToken = new UsernamePasswordAuthenticationToken(authDTO.login(), authDTO.senha());
        manager.authenticate(usuarioAutenticationToken);
        return autenticacaoService.obterToken(authDTO);

    }


}
