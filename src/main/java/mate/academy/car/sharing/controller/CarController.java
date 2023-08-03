package mate.academy.car.sharing.controller;

import lombok.RequiredArgsConstructor;
import mate.academy.car.sharing.dto.request.CarRequestDto;
import mate.academy.car.sharing.dto.response.CarResponseDto;
import mate.academy.car.sharing.entity.Car;
import mate.academy.car.sharing.mapper.CarMapper;
import mate.academy.car.sharing.service.CarService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;
    private final CarMapper carMapper;

    @GetMapping("/")
    public List<CarResponseDto> getAll() {
        return carService.getAll().stream()
                .map(carMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CarResponseDto get(@PathVariable Long id) {
        return carMapper.toDto(carService.getById(id));
    }

    @PostMapping
    public CarResponseDto add(@RequestBody CarRequestDto dto) {
        return carMapper.toDto(carService.add(carMapper.toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        carService.delete(id);
    }

    @PutMapping("/{id}")
    public CarResponseDto updateCar(@PathVariable Long id,
                                    @RequestBody CarRequestDto dto) {
        Car carFromDto = carMapper.toEntity(dto);
        carFromDto.setId(id);
        return carMapper.toDto(carService.update(carFromDto));
    }
}
