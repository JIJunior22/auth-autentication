package io.projeto.security.auth.autenticacao.enums;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum RoleEnum {
    ADMIN("admin"), USER("user");

    private String role;




}
