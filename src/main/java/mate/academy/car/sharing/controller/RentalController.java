package mate.academy.car.sharing.controller;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.car.sharing.dto.response.RentalResponseDto;
import mate.academy.car.sharing.mapper.RentalMapper;
import mate.academy.car.sharing.service.RentalService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rentals")
public class RentalController {
    private final RentalService rentalService;
    private final RentalMapper mapper;

    @GetMapping
    public List<RentalResponseDto> getAll() {
        return RentalService.getAll().stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{rentalId}")
    public RentalResponseDto get(@PathVariable Long rentalId) {
        return mapper.mapToDto(rentalService.getById(rentalId));
    }

}
