package mate.academy.car.sharing.repository;

import java.util.List;
import java.util.Optional;
import mate.academy.car.sharing.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    @Query(nativeQuery = true,
            value = """ 
                    SELECT * FROM rentals AS r 
                    WHERE r.return_date < DATE_ADD(CURDATE(), INTERVAL 1 DAY) AND
                     r.actual_return_date IS NULL
                    """)
    List<Rental> getOverdueRentals();

    @Query(nativeQuery = true,
            value = "SELECT * FROM rentals AS r " +
                    "WHERE r.return_date >= DATE_ADD(CURDATE(), INTERVAL 1 DAY) " +
                    "AND r.actual_return_date IS NULL AND r.user_id = :userId " +
                    "LIMIT 1")
    Optional<Rental> findActualRental(@Param("userId") Long userId);

}
