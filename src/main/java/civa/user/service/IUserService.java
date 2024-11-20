package civa.user.service;

import civa.shared.dto.response.ApiResponse;
import civa.user.model.dto.UserResponseDto;

import java.util.List;

public interface IUserService {

    ApiResponse<UserResponseDto> getUserById(Long userId);

    ApiResponse<List<UserResponseDto>> getAllUser();

    ApiResponse<UserResponseDto> getUserByName(String name);
}
