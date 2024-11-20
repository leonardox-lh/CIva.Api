package civa.transport.services;

import civa.shared.dto.response.ApiResponse;
import civa.transport.models.dto.response.BusResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IBusService {
    ApiResponse<List<BusResponseDto>> getBuses();

    Page<BusResponseDto> getBuses(int page, int size);
    ApiResponse<BusResponseDto> getBustById(Long busId);
}
