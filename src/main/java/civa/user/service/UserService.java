package civa.user.service;

import civa.shared.dto.enums.EStatus;
import civa.shared.dto.response.ApiResponse;
import civa.shared.exception.ResourceNotFoundException;
import civa.user.model.dto.UserResponseDto;
import civa.user.repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    private final IUserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(IUserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<UserResponseDto> getUserById(Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el usuario con id " + userId));

        var userDto = modelMapper.map(user, UserResponseDto.class);
        return new ApiResponse<>("Usuario encontrado", EStatus.SUCCESS, userDto);
    }

    @Override
    public ApiResponse<List<UserResponseDto>> getAllUser() {
        var users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron usuarios");
        }
        var usersDto = modelMapper.map(users, List.class);
        return new ApiResponse<>("Usuarios encontrados", EStatus.SUCCESS, usersDto);
    }

    @Override
    public ApiResponse<UserResponseDto> getUserByName(String name) {
        var user = userRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el usuario con nombre " + name));
        var userDto = modelMapper.map(user, UserResponseDto.class);
        return new ApiResponse<>("Usuario encontrado", EStatus.SUCCESS, userDto);
    }
}
