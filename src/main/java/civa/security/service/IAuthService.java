package civa.security.service;

import civa.security.model.dto.request.LoginRequestDto;
import civa.security.model.dto.request.RegisterRequestDto;
import civa.security.model.dto.response.RegisterUserResponseDto;
import civa.security.model.dto.response.TokenResponseDto;
import civa.shared.dto.response.ApiResponse;

/**
 * Servicio para autenticación y registro de usuarios
 */
public interface IAuthService {
    /**
     * Registra un usuario
     * @param request Datos para el registro
     * @return Usuario registrado
     */
    ApiResponse<RegisterUserResponseDto> registerCompany(RegisterRequestDto request);

    /**
     * Realiza el login del usuario
     * @param request Credenciales
     * @return Token de acceso
     */
    ApiResponse<TokenResponseDto> login(LoginRequestDto request);
}
