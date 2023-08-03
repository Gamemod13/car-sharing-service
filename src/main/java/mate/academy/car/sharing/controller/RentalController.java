package mate.academy.car.sharing.controller;

import java.util.List;
import java.util.stream.Collectors;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import mate.academy.car.sharing.dto.response.RentalResponseDto;
import mate.academy.car.sharing.entity.Rental;
import mate.academy.car.sharing.mapper.RentalMapper;
import mate.academy.car.sharing.service.RentalService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rentals")
public class RentalController {
    private final RentalService rentalService;
    private final RentalMapper mapper;

    @Operation(summary = "Get all rentals", description = "Get all rentals")
    @GetMapping
    public List<RentalResponseDto> getAll() {
        return rentalService.getAll().stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }


    @Operation(summary = "Get rental by ID", description = "Get rental by ID")
    @GetMapping("/{rentalId}")
    public RentalResponseDto get(@PathVariable Long rentalId) {
        return mapper.mapToDto(rentalService.getById(rentalId));
    }

    @Operation(summary = "Save rental in DB", description = "Save rental in DB")
    @PostMapping
    public Rental save(Rental rental) {
        return rentalService.add(rental);
    }

}
