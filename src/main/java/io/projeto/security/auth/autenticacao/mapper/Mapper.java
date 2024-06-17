package io.projeto.security.auth.autenticacao.mapper;

import io.projeto.security.auth.autenticacao.domain.Usuario;
import io.projeto.security.auth.autenticacao.dto.UsuarioDTO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@Data
public class Mapper {
    //Criptografar senha
    @Autowired
    private PasswordEncoder passwordEncoder;


    public Usuario converterParaUsuario(UsuarioDTO usuarioDTO) {
        //passwordHash vai encriptografar a senha
        var passwordHash = passwordEncoder.encode(usuarioDTO.senha());
        Usuario usuario = new Usuario(usuarioDTO.nome(), usuarioDTO.login(), passwordHash, usuarioDTO.role());
        return usuario;
    }

    public UsuarioDTO converterParaDTO(Usuario usuario) {

        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getNome(), usuario.getLogin(), usuario.getSenha(),usuario.getRole());
        return usuarioDTO;
    }
}
