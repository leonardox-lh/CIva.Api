package civa.security.model.dto.response;

import civa.user.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * Clase que representa la respuesta de la API cuando se registra un usuario
 * @author Leonardo Lopez
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserResponseDto {
    private Long userId;
    private String name;
    private Set<Role> roles = new HashSet<>();
}
