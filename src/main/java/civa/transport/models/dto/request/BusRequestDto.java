package civa.transport.models.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusRequestDto {
    @NotEmpty(message = "The bus name is required")
    private String name;

    @NotEmpty(message = "The bus plate is required")
    private String plate;

    @NotEmpty(message = "The bus brand is required")
    private String brand;

    @NotEmpty(message = "The bus availability is required")
    private Boolean available;
}
