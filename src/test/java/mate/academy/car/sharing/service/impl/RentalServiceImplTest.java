package mate.academy.car.sharing.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import mate.academy.car.sharing.entity.Rental;
import mate.academy.car.sharing.repository.RentalRepository;
import mate.academy.car.sharing.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class RentalServiceImplTest {

    @Mock
    private RentalRepository rentalRepository;
    @Mock
    private CarService carService;

    @InjectMocks
    private RentalServiceImpl rentalService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addRentalTest() {
        Rental rental = new Rental();
        rental.setId(1L);
        when(rentalRepository.save(rental)).thenReturn(rental);
        Rental addedRental = rentalService.add(rental);
        assertNotNull(addedRental);
        assertEquals(rental, addedRental);
    }

    @Test
    void getByIdExistingRentalTest() {
        Long rentalId = 1L;
        Rental rental = new Rental();
        rental.setId(rentalId);
        when(rentalRepository.findById(rentalId)).thenReturn(Optional.of(rental));
        Rental retrievedRental = rentalService.getById(rentalId);
        assertNotNull(retrievedRental);
        assertEquals(rental, retrievedRental);
    }

    @Test
    void getAllTest() {
        Rental rental1 = new Rental();
        Rental rental2 = new Rental();
        List<Rental> rentalList = List.of(rental1, rental2);
        when(rentalRepository.findAll()).thenReturn(rentalList);
        List<Rental> retrievedRentals = rentalService.getAll();
        assertNotNull(retrievedRentals);
        assertEquals(2, retrievedRentals.size());
        assertEquals(rental1, retrievedRentals.get(0));
        assertEquals(rental2, retrievedRentals.get(1));
    }

    @Test
    void deleteTest() {
        Long rentalId = 1L;
        rentalService.delete(rentalId);
        verify(rentalRepository).deleteById(rentalId);
    }

    @Test
    void updateRentalTest() {
        Rental rental = new Rental();
        rental.setId(1L);
        when(rentalRepository.save(rental)).thenReturn(rental);
        Rental updatedRental = rentalService.update(rental);
        assertNotNull(updatedRental);
        assertEquals(rental, updatedRental);
    }

    @Test
    void getOverdueRentalsTest() {
        Rental rental1 = new Rental();
        Rental rental2 = new Rental();
        List<Rental> overdueRentals = List.of(rental1, rental2);
        when(rentalRepository.getOverdueRentals()).thenReturn(overdueRentals);
        List<Rental> retrievedOverdueRentals = rentalService.getOverdueRentals();
        assertNotNull(retrievedOverdueRentals);
        assertEquals(2, retrievedOverdueRentals.size());
        assertEquals(rental1, retrievedOverdueRentals.get(0));
        assertEquals(rental2, retrievedOverdueRentals.get(1));
    }

    @Test
    void findActualRentalExistingRentalTest() {
        Long userId = 1L;
        Rental rental = new Rental();
        rental.setId(1L);
        when(rentalRepository.findActualRental(userId)).thenReturn(Optional.of(rental));
        Rental retrievedRental = rentalService.findActualRental(userId);
        assertNotNull(retrievedRental);
        assertEquals(rental, retrievedRental);
    }

    @Test
    void findActualRentalNonExistingRentalTest() {
        Long userId = 1L;
        when(rentalRepository.findActualRental(userId)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> rentalService.findActualRental(userId));
    }
}
