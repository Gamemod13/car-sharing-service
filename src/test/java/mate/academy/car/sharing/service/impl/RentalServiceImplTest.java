package mate.academy.car.sharing.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import mate.academy.car.sharing.entity.Rental;
import mate.academy.car.sharing.repository.RentalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class RentalServiceImplTest {

    @Mock
    private RentalRepository rentalRepository;

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
        Mockito.when(rentalRepository.save(rental)).thenReturn(rental);
        Rental addedRental = rentalService.add(rental);
        assertNotNull(addedRental);
        assertEquals(rental, addedRental);
    }

    @Test
    void getByIdTest() {
        Long rentalId = 1L;
        Rental rental = new Rental();
        rental.setId(rentalId);
        Mockito.when(rentalRepository.getReferenceById(rentalId)).thenReturn(rental);
        Rental retrievedRental = rentalService.getById(rentalId);
        assertNotNull(retrievedRental);
        assertEquals(rental, retrievedRental);
    }

    @Test
    void getAllTest() {
        // Arrange
        Rental rental1 = new Rental();
        Rental rental2 = new Rental();
        List<Rental> rentalList = List.of(rental1, rental2);
        Mockito.when(rentalRepository.findAll()).thenReturn(rentalList);
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
        Mockito.verify(rentalRepository).deleteById(rentalId);
    }

    @Test
    void updateRentalTest() {
        Rental rental = new Rental();
        rental.setId(1L);
        Mockito.when(rentalRepository.save(rental)).thenReturn(rental);
        Rental updatedRental = rentalService.update(rental);
        assertNotNull(updatedRental);
        assertEquals(rental, updatedRental);
    }
}
