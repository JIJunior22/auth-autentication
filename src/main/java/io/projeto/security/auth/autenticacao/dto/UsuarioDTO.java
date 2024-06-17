package io.projeto.security.auth.autenticacao.dto;

import io.projeto.security.auth.autenticacao.enums.RoleEnum;

public record UsuarioDTO(String nome, String login, String senha, RoleEnum role) {
}
