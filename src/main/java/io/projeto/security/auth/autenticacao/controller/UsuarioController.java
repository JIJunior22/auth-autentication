package io.projeto.security.auth.autenticacao.controller;

import io.projeto.security.auth.autenticacao.dto.UsuarioDTO;
import io.projeto.security.auth.autenticacao.services.UsuarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }


    @PostMapping
    private UsuarioDTO salvar(@RequestBody UsuarioDTO usuarioDTO) {
        service.salvar(usuarioDTO);
        return usuarioDTO;
    }
    @GetMapping("/admin")
    private String getAdmin() {
        return "permissão de administrador";
    }

    @GetMapping("/user")
    private String getUser() {
        return "permissão de usuário";
    }

}

