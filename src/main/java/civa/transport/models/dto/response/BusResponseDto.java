package civa.transport.models.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusResponseDto {
    private Long busId;
    private String name;
    private String plate;
    private Date dateTime;
    private String brand;
    private Boolean available;
}
