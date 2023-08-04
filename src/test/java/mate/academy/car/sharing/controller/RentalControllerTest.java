package mate.academy.car.sharing.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import mate.academy.car.sharing.dto.request.RentalRequestDto;
import mate.academy.car.sharing.dto.response.RentalResponseDto;
import mate.academy.car.sharing.entity.Rental;
import mate.academy.car.sharing.mapper.RentalMapper;
import mate.academy.car.sharing.service.RentalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class RentalControllerTest {
    @Mock
    private RentalService rentalService;
    @Mock
    private RentalMapper rentalMapper;
    @InjectMocks
    private RentalController rentalController;

    @Test
    public void testAdd_ReturnsRentalResponseDto() {
        RentalRequestDto requestDto = new RentalRequestDto();
        Rental rental = new Rental();

        when(rentalMapper.mapToEntity(requestDto)).thenReturn(rental);
        when(rentalService.add(rental)).thenReturn(rental);
        when(rentalMapper.mapToDto(rental)).thenReturn(new RentalResponseDto());

        RentalResponseDto result = rentalController.add(requestDto);

        assertNotNull(result);
    }

    @Test
    public void testGetByUserAndStatus_ReturnsListOfRentalResponseDto() {
        Long userId = 1L;
        Boolean isActive = true;
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental());
        rentals.add(new Rental());
        when(rentalService.getByUserAndStatus(userId, isActive)).thenReturn(rentals);
        when(rentalMapper.mapToDto(any(Rental.class))).thenReturn(new RentalResponseDto());

        List<RentalResponseDto> result = rentalController.getByUserAndStatus(userId, isActive);

        assertNotNull(result);
        assertEquals(rentals.size(), result.size());
    }

    @Test
    public void testGet_ReturnsRentalResponseDto() {
        Long rentalId = 1L;
        Rental rental = new Rental();
        when(rentalService.getById(rentalId)).thenReturn(rental);
        when(rentalMapper.mapToDto(rental)).thenReturn(new RentalResponseDto());

        RentalResponseDto result = rentalController.get(rentalId);

        assertNotNull(result);
    }

    @Test
    public void testReturnRental_ReturnsRentalResponseDto() {
        Long rentalId = 1L;
        Rental rental = new Rental();
        when(rentalService.returnRental(rentalId)).thenReturn(rental);
        when(rentalMapper.mapToDto(rental)).thenReturn(new RentalResponseDto());

        RentalResponseDto result = rentalController.returnRental(rentalId);

        assertNotNull(result);
    }
}
