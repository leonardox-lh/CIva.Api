package civa.user.model.dto;

import civa.user.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Long userId;
    private String name;
    private String password;
    private Set<Role> roles = new HashSet<>();
}
