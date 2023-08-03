package mate.academy.car.sharing.repository;

import java.util.List;
import mate.academy.car.sharing.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    @Query("FROM Rental r WHERE r.actualReturnDate is NULL AND r.returnDate >= CURRENT_DATE")
    List<Rental> findAllActual();
}
