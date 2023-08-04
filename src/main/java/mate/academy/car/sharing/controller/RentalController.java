package mate.academy.car.sharing.controller;

import java.util.List;
import java.util.stream.Collectors;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import mate.academy.car.sharing.dto.request.RentalRequestDto;
import mate.academy.car.sharing.dto.response.RentalResponseDto;
import mate.academy.car.sharing.entity.Rental;
import mate.academy.car.sharing.mapper.RentalMapper;
import mate.academy.car.sharing.service.RentalService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rentals")
public class RentalController {
    private final RentalService rentalService;
    private final RentalMapper mapper;

    @Operation(summary = "Add a new rental", description =
            "Add a new rental and decrease decrease car inventory by 1")
    @PostMapping
    public RentalResponseDto add(@Valid RentalRequestDto requestDto) {
        Rental rental = mapper.mapToEntity(requestDto);
        return mapper.mapToDto(rentalService.add(rental));
    }

    @Operation(summary = "get rentals by user ID and whether the rental is still active or not",
            description = "get rentals by user ID and whether the rental is still active or not")
    @GetMapping
    public List<RentalResponseDto> getByUserAndStatus(@RequestParam Long userId,
                                                      @RequestParam Boolean isActive) {
        return rentalService.getByUserAndStatus(userId, isActive).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Operation(summary = "get specific rental", description = "get specific rental")
    @GetMapping("/{id}")
    public RentalResponseDto get(@PathVariable Long id) {
        return mapper.mapToDto(rentalService.getById(id));
    }

    @Operation(summary = "set actual return date", description =
            "set actual return date and increase car inventory by 1")
    @PostMapping("/{id}/return")
    public RentalResponseDto returnRental(@PathVariable Long id) {
        return mapper.mapToDto(rentalService.returnRental(id));
    }
}
