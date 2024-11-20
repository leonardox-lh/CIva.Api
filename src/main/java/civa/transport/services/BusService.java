package civa.transport.services;

import civa.shared.dto.enums.EStatus;
import civa.shared.dto.response.ApiResponse;
import civa.transport.models.dto.response.BusResponseDto;
import civa.transport.repositories.IBusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class BusService implements IBusService {

    private final IBusRepository iBusRepository;
    private final ModelMapper modelMapper;

    public BusService(IBusRepository iBusRepository, ModelMapper modelMapper) {
        this.iBusRepository = iBusRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<List<BusResponseDto>> getBuses() {
        var buses = iBusRepository.findAll();
        var busesDto = buses.stream()
                .map(bus -> modelMapper.map(bus, BusResponseDto.class))
                .toList();
        return new ApiResponse<>("Buses encontrados", EStatus.SUCCESS, busesDto);
    }

    @Override
    public Page<BusResponseDto> getBuses(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var buses = iBusRepository.findAll(pageable);
        return buses.map(bus -> modelMapper.map(bus, BusResponseDto.class));
    }

    @Override
    public ApiResponse<BusResponseDto> getBustById(Long busId) {
        var bus = iBusRepository.findById(busId)
                .orElseThrow(() -> new RuntimeException("Bus con id " + busId + " no encontrado"));
        var busDto = modelMapper.map(bus, BusResponseDto.class);
        return new ApiResponse<>("Bus encontrado", EStatus.SUCCESS, busDto);
    }
}
