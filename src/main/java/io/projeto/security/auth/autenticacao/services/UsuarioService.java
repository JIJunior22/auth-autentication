package io.projeto.security.auth.autenticacao.services;

import io.projeto.security.auth.autenticacao.domain.Usuario;
import io.projeto.security.auth.autenticacao.dto.UsuarioDTO;
import io.projeto.security.auth.autenticacao.mapper.Mapper;
import io.projeto.security.auth.autenticacao.repository.UsuarioRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Data
public class UsuarioService {
    @Autowired
    private final UsuarioRepository repository;
    @Autowired
    private final Mapper mapper;




    public UsuarioService(UsuarioRepository repository, Mapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ResponseEntity salvar(UsuarioDTO usuarioDTO) {
        Usuario existente = getByLogin(usuarioDTO.login());
        if (existente != null) {
            throw new RuntimeException("Usuario já existe");
        }
        //Conversão simples de tipos(Usuario para DTO)
//        Usuario usuario = new Usuario(usuarioDTO.nome(),usuarioDTO.login(),usuarioDTO.senha());
        Usuario user = mapper.converterParaUsuario(usuarioDTO);
        repository.save(user);
        return ResponseEntity.ok().build();

    }
//    public List<UsuarioDTO>listar(){
//
//
//        return repository.findAll();
//    }

    public Usuario getByLogin(String login) {


        return null;
    }


}
