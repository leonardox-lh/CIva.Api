package civa.user.controller;

import civa.shared.dto.response.ApiResponse;
import civa.user.model.dto.UserResponseDto;
import civa.user.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador para usuarios
 * @author Leonardo Lopez
 */
@Tag(name = "User")
@RequestMapping("/api/v1/users")
@RestController
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    // URL: http://localhost:8080/api/v1/users
    @Operation(summary = "Obtiene todos los usuarios")
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponseDto>>> getAllUsers() {
        var res = userService.getAllUser();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
