package civa.transport.controller;

import civa.shared.dto.response.ApiResponse;
import civa.transport.models.dto.response.BusResponseDto;
import civa.transport.services.IBusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Bus")
@RequestMapping("/api/v1")
@RestController
public class BusController {
    private final IBusService busService;

    public BusController(IBusService busService) {
        this.busService = busService;
    }

    // URL: http://localhost:8080/api/v1/bus
    @Operation(summary = "Obtiene todos los buses")
    @GetMapping("/bus")
    public ResponseEntity<ApiResponse<List<BusResponseDto>>> getBuses() {
        var res = busService.getBuses();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/v1/buses
    @Operation(summary = "Obtiene los buses paginados")
    @GetMapping("/buses")
    public ResponseEntity<Page<BusResponseDto>> getBuses(@RequestParam int page, @RequestParam int size) {
        var res = busService.getBuses(page, size);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/v1/bus/{busId}
    @Operation(summary = "Obtiene el bus por id")
    @GetMapping("/bus/{busId}")
    public ResponseEntity<ApiResponse<BusResponseDto>> getBusById(@PathVariable Long busId) {
        var res = busService.getBustById(busId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
