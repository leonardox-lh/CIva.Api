package civa.security.service;

import civa.security.jwt.provider.JwtTokenProvider;
import civa.security.model.dto.request.LoginRequestDto;
import civa.security.model.dto.request.RegisterRequestDto;
import civa.security.model.dto.response.RegisterUserResponseDto;
import civa.security.model.dto.response.TokenResponseDto;
import civa.shared.dto.enums.EStatus;
import civa.shared.dto.response.ApiResponse;
import civa.shared.exception.CustomException;
import civa.user.model.entity.User;
import civa.user.model.enums.ERole;
import civa.user.repository.IRoleRepository;
import civa.user.repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Servicio para autenticación y registro de usuarios
 */
@Service
public class AuthService implements IAuthService {
    private final AuthenticationManager authenticationManager;
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final ModelMapper modelMapper;

    public AuthService(AuthenticationManager authenticationManager,IUserRepository userRepository, IRoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, ModelMapper modelMapper) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<RegisterUserResponseDto> registerCompany(RegisterRequestDto request) {
        if (userRepository.existsByName(request.getName())) {
            throw new CustomException(HttpStatus.CONFLICT, "El name '" + request.getName() + "' ya está registrado");
        }

        var user = User.builder()
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        var roles = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "No se pudo registrar el usuario, no se encontró el rol USER"));
        user.setRoles(Collections.singleton(roles));


        var newUser = userRepository.save(user);

        var responseData = modelMapper.map(newUser, RegisterUserResponseDto.class);

        return new ApiResponse<>("Registro correcto", EStatus.SUCCESS, responseData);
    }

    @Override
    public ApiResponse<TokenResponseDto> login(LoginRequestDto request) {
        //se validan las credenciales
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getName(),
                        request.getPassword()
                )
        );

        //establece la seguridad
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //se obtiene el token
        String token = jwtTokenProvider.generateToken(authentication);

        var responseData = new TokenResponseDto(token);
        return new ApiResponse<>("Autenticación correcta", EStatus.SUCCESS, responseData);
    }
}
