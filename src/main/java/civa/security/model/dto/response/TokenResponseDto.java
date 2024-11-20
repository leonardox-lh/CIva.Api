package civa.security.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa la respuesta de la API cuando se registra un usuario y se le devuelve el token de acceso
 * @author Leonardo Lopez
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponseDto {
    private String token;
}
